import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Source
import scala.annotation.meta.field
import java.nio.charset.CodingErrorAction
import scala.io.Codec

class  MostPopularBroadCast(
  val viewsPath: String,
  val moviesPath: String)
extends Serializable {

  //initialize the names map
  var movieNamesMap: Map[Int, String] = Map()
  var movieIdMap: Map[String, Int] = Map()
  loadMovieNames()

  //read the file and load it into a Map
  def loadMovieNames() {
    println("Loading Movies Names")
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    val lines = Source.fromFile(moviesPath).getLines()
    for(line <- lines)
    {
      var fields = line.split('|')
      if(fields.length > 1){
        movieNamesMap += (fields(0).toInt -> fields(1))
        movieIdMap += (fields(1) -> fields(0).toInt)
      }
    }
  }

  def parseLine(line: String) =
  {
    val fields = line.split("\t")
    //movie id
    val film = fields(1).toInt
    (film, 1)
  }


  def execute()  {
    val sc = new SparkContext("local[*]", "Most Popular")

    //make the map available to every executor node
    val nameDict = sc.broadcast(movieNamesMap)
    val idDict = sc.broadcast(movieIdMap)

    val lines = sc.textFile(viewsPath)

    val rdd = lines.map(parseLine)

    val movieViewCount = rdd.reduceByKey((x, y) => x +y)
    val movieNamesCount = movieViewCount.map(x => (nameDict.value(x._1),x._2))

    val sortByViewCount = movieNamesCount.map( x => (x._2, x._1)).sortByKey(false)

    val results = sortByViewCount.collect()
    println(s"The most popular movie is: ${results(0)._2} id: ${idDict.value(results(0)._2)}")

  }

}


