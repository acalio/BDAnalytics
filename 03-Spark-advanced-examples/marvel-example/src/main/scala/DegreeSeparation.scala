import org.apache.spark._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.annotation.meta.field
import scala.collection.mutable.ArrayBuffer
import scala.math.min
import scala.math.max
import scala.util.control.Breaks._

import org.apache.spark.util.LongAccumulator




class DegreeSeparation(
  val path:  String,
  val startingCharacterId: Long,
  val targetCharacterId: Long)
extends Serializable {

  //sort of enumeration
  val NotVisited: Int = 0
  val InQueue: Int = 1
  val Visited: Int = 2


  // Accumulator to mark signal that the targetCharacter has been reached
  var hitCounter: Option[LongAccumulator] = None

  // create new type for the BFS algorithm
  type BFSData = (Array[Int], Int, Int) // array of neighbors, distance (from the source node), status
  type BFSNode = (Int, BFSData) // a node in the BFS tree


  def convertToBFS(line: String): BFSNode =
  {
    val fields = line.split("\\s+")
    val heroId = fields(0).toInt

    //get the array of neighbors
    var neighbors: ArrayBuffer[Int] = ArrayBuffer()
    for(index <- 1 to (fields.length-1))
      neighbors += fields(index).toInt

    var status: Int = NotVisited 
    var distance: Int = 9999  //distance from the source node

    if(heroId==startingCharacterId)
    {
      distance = 0
      status = InQueue
    }
    return (heroId, (neighbors.toArray, distance, status))
  }

  //in each iteration, a BFSNode is received, then we go thorough
  //each of its neighbors and create a BFSNode which will be
  //further added into the RDD
  def BFSmap(node: BFSNode): Array[BFSNode] =
  {
    //Extract the data from the received Node
    val characterID: Int = node._1
    val data: BFSData = node._2

    //get the coneections of characterID
    val connections: Array[Int] = data._1
    val currentDistance: Int = data._2
    var status: Int = data._3

    //iterate over this node neighbors
    var neighborsNodes: ArrayBuffer[BFSNode] = ArrayBuffer()

    if(status == InQueue) //it has not be expanded yet
    {
      //expands all of its neighbors
      for(neighbor <- connections)
      {
        //signal to other nodes that the target node has been reached
        if (neighbor == targetCharacterId)
          if(hitCounter.isDefined)
            hitCounter.get.add(1)

        //add the node to the visiting queue
        val newEntry: BFSNode = (neighbor, (Array(), currentDistance+1, InQueue))
        neighborsNodes += newEntry
      }
      status = Visited
    }
    

    //the original node needs to be inserted once again
    //otherwise we would expand it every time it is reached by any of
    //its incoming edges
    val thisEntry: BFSNode = (characterID, (connections, currentDistance, status))
    neighborsNodes += thisEntry
    

    return neighborsNodes.toArray
  }

  // the reduce step only needs to preserve the original data
  // given two entries corresponding to the same hero, we want to:
  // preserve the darkest colour and keep the min distance
  def BFSReduce(data1:BFSData, data2:BFSData): BFSData =
  {
    //determines the min distance
    val distance = min(data1._2, data2._2)

    val status = max(data1._3, data2._3)

    ((data1._1++data2._1).toArray, distance, status)
  }

  def execute() {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "PopularHero")

    // val graph = sc.textFile("/home/antonio/git/BDAnalytics/data/Marvel-graph.txt")

    val graph = sc.textFile(path)
    var rdd = graph.map(convertToBFS)
    var iteration: Int = 0
    var found: Boolean = false

    hitCounter = Some(sc.longAccumulator("Hit Counter"))
    breakable
    {
      for(iteration <- 0 to 10)
      {
        println(s"Iteration: $iteration")
        val iterationRdd = rdd.flatMap(BFSmap)

        println("Processed: "+iterationRdd.count())

        if(hitCounter.isDefined)
          found = hitCounter.get.value>0

        if(found)
          break

        rdd = iterationRdd.reduceByKey(BFSReduce)
      }
    }
    val hitCount = hitCounter.get.value
    println(s"Target Hit $hitCount")

  }
   
}



