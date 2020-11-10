import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.annotation.meta.field


object BetterWordCount
{



  def main(args: Array[String]) =
  {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "Word Count")
    val path = "/home/antonio/git/BDAnalytics/word-flatmap-example/data/book.txt"

    val lines = sc.textFile(path)



    //the problem with this method is that it retuns a scala-map
    //which is not suited for cluster based computation.
    //we want an Rdd instead

    //split with a regular expression - only actual words are taken into account
    // val words = lines.flatMap(x => x.split("\\W+"))
    // val lowerWords = words.flatMap(x => x.toLowerCase())
    // val wordCounts = words.countByValue()

    //each word is first mapped to its lower case and then we create an key/value pair RDD
    //wicht is further reduced by reducebykey
    val wordCounts = lines.flatMap(x=>x.split("\\W+")).map(x => x.toLowerCase()).map(x => (x,1)).reduceByKey((x, y) => x+y)

    //we want to sort elements by theirfrequencey. We need to make the frequency
    //a key fo the RDD and then apply the sortByKey function.
    //we just need to invert the order of each key/value pair
    val sortedKeys = wordCounts.map(x => (x._2, x._1)).sortByKey()

    val results = sortedKeys.collect()

    for(results <- results)
    {
      val count = results._1
      val word = results._2
      println(s"$word: $count")
    }
  }

}



