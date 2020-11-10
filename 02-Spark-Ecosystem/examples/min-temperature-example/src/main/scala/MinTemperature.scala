 
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.annotation.meta.field
import scala.math.min
object MinTemperature
{



  def parseLine(line: String) =
  {
    val fields = line.split(",")
    val stationId = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat

    (stationId, entryType, temperature)
  }

  


  def main(args: Array[String]) =
  {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "FriendsByAge")
    val path = "data/1800.csv"

    val lines = sc.textFile(path)

    val rdd = lines.map(parseLine)

    val minTemps = rdd.filter( x => x._2 == "TMIN")

    val stationTemps = minTemps.map(x => (x._1, x._3))

    val minTempsByStation = stationTemps.reduceByKey((x,y) => min(x,y))

    
    val results = minTempsByStation.collect()

    for(result <- results.sorted)
    {
      val station = result._1
      val temp = result._2

      val formattedTemp = f"$temp%.2f C"
      println(s"$station minimum temperatture: $formattedTemp")
    }


  }

}


