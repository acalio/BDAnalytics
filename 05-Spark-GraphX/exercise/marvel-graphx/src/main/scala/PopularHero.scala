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
    // <---------- Your Code Here ------------>
    
  }


}


