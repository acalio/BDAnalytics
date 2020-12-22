import java.{util => ju}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession


class WC(appConfig: ju.Properties) {


  def execute() {

    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .getOrCreate()

    import spark.implicits._

    // Create DataFrame representing the stream of input lines from connection to localhost:9999
    val lines = spark.readStream
      .format("socket")
      .option("host", appConfig.get(WCParams.SOURCE_ADDRESS_KEY).toString)
      .option("port", appConfig.get(WCParams.SOURCE_PORT_KEY).toString)
      .load()

    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(" "))
    
    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    //run the above query and print the result to theh console
    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()
  }
}

object WCParams {
  val SOURCE_ADDRESS_KEY: String = "source.address"
  val SOURCE_PORT_KEY: String = "source.port"
}
