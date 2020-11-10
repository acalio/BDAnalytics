 
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object TotalAmountSpent
{



  def parseLine(line: String) =
  {
    val fields = line.split(",")
    val customer = fields(0).toInt
    val amount = fields(2).toDouble;

    (customer, amount)
  }


  def main(args: Array[String]) =
  {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "Amount Spent")
    val path = "/home/antonio/git/BDAnalytics/data/customer-orders.csv"

    val lines = sc.textFile(path)

    val rdd = lines.map(parseLine)

    val customerAmountSpent = rdd.reduceByKey((x, y) => x+y)

    val sortedResult = customerAmountSpent.map( x => (x._2, x._1)).sortByKey()

    val results = sortedResult.collect()

    //sort by total amount
    results.foreach(println)
  }

}
