package io.acalio.tasks

import io.acalio.utility.Keys
import java.{util => ju}
import org.apache.spark.sql.SparkSession
import za.co.absa.abris.config.AbrisConfig
import za.co.absa.abris.avro.read.confluent.SchemaManagerFactory
import za.co.absa.abris.avro.functions.from_avro

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row


class Sniffer(
  override val name: String,
  val entity: String,
  override val outFormat: String,
  override val applicationProperties: ju.Properties
) extends SparkTask(name, "kafka", outFormat, applicationProperties) {

  def execute() {
    val topic: String = s"$entity-${applicationProperties.get(Keys.TOPIC).toString}"
    //override the topic into the application properties
    val entityDf: Dataset[Row] = readFromKafka(topic)
    entityDf.printSchema()
    
    //output configuration
    if(outFormat=="console"){
      writeStreamToConsole(entityDf, s"/tmp/$entity")
    }else if(outFormat=="csv"){
      val path: String = applicationProperties.get(Keys.CSV_SINK).toString
      println(s"writing to $path")
      writeStreamToCsv(entityDf, path, s"/tmp/$entity")
    }
  }
}













