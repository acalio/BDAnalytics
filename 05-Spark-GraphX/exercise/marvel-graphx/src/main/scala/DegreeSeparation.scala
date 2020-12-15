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

    val bfs = initialGraph.pregel(Double.PositiveInfinity, 10)( 
        // Our "vertex program" preserves the shortest distance
        // between an inbound message and its current value.
        // It receives the vertex ID we are operating on,
        // the attribute already stored with the vertex, and
        // the inbound message from this iteration.
        (id, attr, msg) => math.min(attr, msg), 
        
        // Our "send message" function propagates out to all neighbors
        // with the distance incremented by one.
        triplet => { 
          if (triplet.srcAttr != Double.PositiveInfinity) { 
            Iterator((triplet.dstId, triplet.srcAttr+1)) 
          } else { 
            Iterator.empty 
          } 
        }, 
        
        // The "reduce" operation preserves the minimum
        // of messages received by a vertex if multiple
        // messages are received by one vertex
      (a,b) => math.min(a,b)
    ).cache()
    
    // Print out the first 100 results:
    bfs.vertices.join(vertices).take(100).foreach(println)
    
    // Recreate our "degrees of separation" result:
    println(s"\n\nDegrees from $startingCharacterId to $targetCharacterId ")  // ADAM 3031 is hero ID 14
    bfs.vertices.filter(x => x._1 == targetCharacterId).collect.foreach(println)

  }

}
