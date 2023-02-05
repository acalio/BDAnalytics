import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.twitter.TwitterUtils
import Utilities._


object SaveTweets
{

  def main(args: Array[String]) {


    val ssc = new StreamingContext("local[*]", "SaveTweets", Seconds(1))
    
    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val tweets = TwitterUtils.createStream(ssc, None)
    
    // Now extract the text of each status update into DStreams using map()
    val statuses = tweets.map(_.getText())
    
    var totalTweets: Long = 0

    //iterate for each RDD within the current dstream
    statuses.foreachRDD((rdd, timestamp)=>{
      //let's check if this RDD is not empty
      if(rdd.count() > 0) {
        //combine every partition this RDD is splitted into to a single one
        //this operation is needed in order to consolidate the results distriubted
        //among the different partititons of this RDD
        val singlePartition = rdd.repartition(1).cache()
        totalTweets += singlePartition.count()
        println("Tweet Count: "+totalTweets)
        if(totalTweets > 1000)
          System.exit(1)
      }
    })
    // Set a checkpoint directory, and kick it all off
    // I could watch this all day!
    ssc.checkpoint("/tmp/checkpoint/")
    ssc.start()
    ssc.awaitTermination()
  }
}
