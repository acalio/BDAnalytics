package io.acalio.tasks

import java.{util => ju}
import za.co.absa.abris.config.FromAvroConfig
import za.co.absa.abris.config.AbrisConfig
import io.acalio.utility.Keys
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import za.co.absa.abris.config.AbrisConfig
import za.co.absa.abris.avro.functions.from_avro

abstract class  SparkTask(
  val name: String,
  val inFormat: String,
  val outFormat: String,
  val applicationProperties: ju.Properties
) {

  val spark = SparkSession.builder
      .appName(name)
      .master("local[*]")
      .getOrCreate()

  def execute() 

  protected def readFromKafka(topic: String): Dataset[Row] = {
    val df = spark.readStream
      .format("kafka")
      .option("subscribe", topic) //the topic must point to a specific entity
      .option(Keys.BROKER_URL, applicationProperties.get(Keys.BROKER_URL).toString)
      .option(Keys.OFFSET, applicationProperties.get(Keys.OFFSET).toString)
      .load()

    val abrisConfig = configureAvro(topic)

    val entityDf = df.select(from_avro(col("value"), abrisConfig) as 'entity)
      .select("entity.*")
    return entityDf
  }


  def readFromCsv(): Dataset[Row]  = {
    //set configuration for aws
    val sourcePath: String  = applicationProperties.get(Keys.CSV_SINK).toString
    println(s"Reading from: $sourcePath")
      val df = spark
        .read
        .csv(sourcePath)
    return df
  }


  protected def writeStreamToCsv(df: Dataset[Row], path: String, checkPoint: String) {

    df.writeStream
      .format("csv")
      .outputMode("append")
      .option("path", path)
      .option("checkpointLocation",checkPoint)
      .start()
      .awaitTermination()
  }

  def writeStaticToCsv(df: Dataset[Row], path: String) {
    df.write.csv(path)
  }

  def writeStreamToConsole(df: Dataset[Row], checkPoint: String) {
    df.writeStream
      .format("console")
      .outputMode("append")
      .option("checkpointLocation", checkPoint)
      .start()
      .awaitTermination()
  }

  protected def configureAvro(topic: String): FromAvroConfig = {
    return AbrisConfig
      .fromConfluentAvro
      .downloadReaderSchemaByLatestVersion
      .andTopicNameStrategy(topic)
      .usingSchemaRegistry(applicationProperties.get(Keys.SCHEMA_REGISTRY_URL).toString)
  }

}
