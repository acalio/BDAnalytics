import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.twitter.TwitterUtils
import Utilities._
import java.util.concurrent.atomic.AtomicLong

/**
  * We will learn how to do the following operations:
  * - Filtering a DStream
  * - Flatmap vs map
  * - Using windowed transforms
  * - Key/value RDD
  * - Sorting RDD
  *  Exercise: Experiment with different batch/window/slide sizes. 
  *  Modify the script to keep track 
  *  of the most popular words used in tweets not the hashtag
  */
object MostPopularHashtag
{

  def main(args: Array[String]) {


    val ssc = new StreamingContext("local[*]", "AverageTweetsLength", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into DStreams using map()
    val tweetsWords = tweets.map(_.getText()).flatMap(_.split(" "))

    //keep only the hashtags
    val hashtags = tweetsWords.filter(_.startsWith("#"))

    val hashtagsKeyValues = hashtags.map(x => (x,1))

    val hashtagsCount = hashtagsKeyValues.reduceByKeyAndWindow(
      (x,y)=> x+y,
      (x,y)=> x-y,// what happends when an entry fades out of the current window - decrease the counter
      Seconds(300),
      Seconds(1)
    )

    //the function is applied upoon every RDD within the DStream
    val sortedResults = hashtagsCount.transform(rdd => rdd.sortBy(x=>x._2, false))
    sortedResults.print

    // Set a checkpoint directory, and kick it all off
    // I could watch this all day!
    ssc.checkpoint("/tmp/checkpoint/")
    ssc.start()
    ssc.awaitTermination()
  }
}
