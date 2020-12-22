package io.acalio.tasks
import java.{util => ju}
import org.apache.spark.sql.SparkSession
import io.acalio.utility.Keys
import za.co.absa.abris.config.AbrisConfig
import za.co.absa.abris.avro.functions.from_avro
import org.apache.spark.sql.functions.{udf, col}
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.streaming.DataStreamWriter
import io.acalio.utility.sentiment.SentimentAnalyzer
import scala.collection.JavaConverters._

/**
  * Class for extracting the interaction graph 
  * from the comment threads
  * 
  * @constructor create a new InteractionGraph
  * @param name the name of the spark context
  * @param inFormat where to read data. 
  *        if "kafka" comments are read from the kafka topic
  *        if "parquet" they are stored in parquet inFormat on aws
  * @param properties to initialize the task
  */
class InteractionGraph(
  override val name: String,
  override val inFormat: String, // inFormat = kafka if you want to read from kafka,
  override val outFormat: String,
  override val applicationProperties: ju.Properties,
) extends SparkTask(name, inFormat, outFormat, applicationProperties) {

  def execute() {
    var commentsDf: Dataset[Row] = null
    var streamingContext: Boolean = false
    inFormat match {
      case "kafka" => {
        var topic: String = s"comment-${applicationProperties.get(Keys.TOPIC).toString}"
        commentsDf = readFromKafka(topic)
        streamingContext = true
      }
      case "csv" => {
        commentsDf = readFromCsv()
      }
    }
    commentsDf.printSchema()
    val interactionDf = extractInteractionGraph(commentsDf)
    outFormat match {
      case "console" => {
        if(streamingContext)
          writeStreamToConsole(interactionDf, "/tmp/interaction")
        else
          interactionDf.show()
      }
      case "csv" => {
        val path: String = applicationProperties.get(Keys.CSV_SINK).toString
        if(streamingContext)
          writeStreamToCsv(interactionDf, path, "/tmp/interaction" )
        else
          writeStaticToCsv(interactionDf, path)
      }
    }
  }

  private def extractInteractionGraph(commentsDf : Dataset[Row]): Dataset[Row] = {
    import spark.implicits._
    val sentimentUDF = udf {
      text: String =>  SentimentAnalyzer.sentiment(text)
    }
    val interactionDf = commentsDf
      .as("left")
      .join(commentsDf.as("right"), $"right.parentID" === $"left.id")
      .select(
        col("left.authorChannelId").as("parent"),
        col("right.authorChannelId").as("child"),
        col("right.text"))
      .withColumn("text", sentimentUDF($"text"))

    return interactionDf
  }

}












