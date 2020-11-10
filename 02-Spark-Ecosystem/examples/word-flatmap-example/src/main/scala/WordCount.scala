import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.annotation.meta.field


object WordCount
{



  def main(args: Array[String]) =
  {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "Word Count")
    val path = "/home/antonio/git/BDAnalytics/word-flatmap-example/data/book.txt"

    val lines = sc.textFile(path)
    //creates an RDD of unaryu values - each entry is simply a word in the file
    val words = lines.flatMap(x => x.split(" "))

    val wordCounts = words.countByValue()

    wordCounts.foreach(println)
  }

}



