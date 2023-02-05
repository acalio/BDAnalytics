import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.twitter.TwitterUtils
import Utilities._
import java.util.concurrent.atomic.AtomicLong


object AverageTweetsLength
{

  def main(args: Array[String]) {


    val ssc = new StreamingContext("local[*]", "AverageTweetsLength", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into DStreams using map()
    val lengths = tweets.map(_.getText()).map(x => x.length)


    //We need thread safety because within each executor we are running in a mult-thread fashion
    var totalTweets = new AtomicLong()
    var totalLength = new AtomicLong()

    lengths.foreachRDD((rdd, time) => {
      var count = rdd.count()
      if(count > 0) {
        totalTweets.getAndAdd(count)
        totalLength.getAndAdd(rdd.reduce( (x,y)=> x+y))
        println("Total Tweets: " + totalTweets.get())
        println("Total Length: " + totalLength.get())
        println("Average: "+totalLength.get()/totalTweets.get())
      }
    })
    // Set a checkpoint directory, and kick it all off
    // I could watch this all day!
    ssc.checkpoint("/tmp/checkpoint/")
    ssc.start()
    ssc.awaitTermination()
  }
}
