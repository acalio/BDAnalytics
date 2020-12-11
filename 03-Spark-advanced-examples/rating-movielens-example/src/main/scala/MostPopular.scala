import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

class MostPopular(
  val path: String
)
extends Serializable {

  def parseLine(line: String) =
  {
    val fields = line.split("\t")
    //movie id
    val film = fields(1).toInt
    (film, 1)
  }


  def execute() 
  {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "Most Popular")
//    val path = "/home/antonio/git/BDAnalytics/data/u.data"

    val lines = sc.textFile(path)

    val rdd = lines.map(parseLine)

    val movieViewCount = rdd.reduceByKey((x, y) => x +y)

    val sortByViewCount = movieViewCount.map( x => (x._2, x._1)).sortByKey(false)

    val results = sortByViewCount.collect()

    results.foreach(println)

  }

}


