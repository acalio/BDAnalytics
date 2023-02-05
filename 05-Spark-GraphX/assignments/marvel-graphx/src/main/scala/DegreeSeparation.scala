import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._


class DegreeSeparation(
  val namesPath:  String,
  val graphPath:  String,
  val startingCharacterId: Long,
  val targetCharacterId: Long)
extends Serializable {

  def execute() {
    val sc = new SparkContext("local[*]", "PopularHero")

    val nameLines = sc.textFile(namesPath)
    //flat map because there might be bad formatted lines
    val vertices = nameLines.flatMap(Utility.makeVertices) 

    val edgeLines = sc.textFile(graphPath)
    val edges = edgeLines.flatMap(Utility.makeEdges)
    
    //create the graph
    // Define a default user in case there are relationship with missing user
    val defaultUser = "Walker  Texas Ranger"
    // Build the initial Graph
    val graph = Graph(vertices, edges, defaultUser)

    //breadth-first search
    println("Computing the degree of separation")
    val root: VertexId = startingCharacterId
    //initialize eacho node with the distance to infinity
    val initialGraph = graph.mapVertices((id, _)=> if(id == root) 0.0 else Double.PositiveInfinity)

    //use the pregel API for Breadth First Search
    //<-------- Your Code Here ----------->
  }

}
