import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._


class PopularHero(
  val graphPath: String,
  val namesPath: String)
extends Serializable {


  def heroCoOccurences(line: String)  =
  {
    var fields = line.split(' ')
    (fields(0).toInt, fields.length-1)
  }

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
    println("The top 10 most-connected heroes")
    graph.
      degrees //compute the degree of eachg node
      .join(vertices) //join with the vertices RDD
      .sortBy(_._2._1, ascending=false) //sort in descending order
      .take(10) //take the first ten
      .foreach(println) //print them to console
    
  }


}


