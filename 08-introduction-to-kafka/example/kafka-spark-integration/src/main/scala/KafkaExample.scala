import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel

import java.util.regex.Pattern
import java.util.regex.Matcher

import Utilities._

import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.kafka.common.serialization.StringDeserializer


/**
  * Rifare lo stesso esercizio usando structured streaming
  * 
  */
object KafkaExample {


  def main(args: Array[String]) = {

    val ssc = new StreamingContext("local[*]", "KafkaExample", Seconds(1))

    setupLogging()

    val kafkaParams = Map[String, Object] (
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "myGroup",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)

    )

    val topics = List("testLogs").toSet

    val lines = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )map(_.value)

    lines.print

    val pattern: Pattern = apacheLogParser()

    val requests = lines.map(
       x  => {
         val matcher: Matcher = pattern.matcher(x)
         if(matcher.matches())
           matcher.group(5)
       }
     )

    val urls = requests.map(
      x => {
        val arr = x.toString.split(" ")
        if(arr.size == 3)
          arr(1)
        else
          "error"
      }
    )

    val urlCounts = urls.map(x => (x,1))
      .reduceByKeyAndWindow(
        _ + _,
        _ - _,
        Seconds(300),
        Seconds(1))

    val sortedResults = urlCounts.transform(rdd => rdd.sortBy(x=> x._2, false))
    sortedResults.print

    ssc.checkpoint("/tmp/")
    ssc.start()
    ssc.awaitTermination()

  }
}
