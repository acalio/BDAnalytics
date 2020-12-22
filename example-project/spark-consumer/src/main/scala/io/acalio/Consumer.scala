
package io.acalio
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}
import org.apache.spark.sql

import za.co.absa.abris.avro.functions.from_avro
import za.co.absa.abris.config.AbrisConfig
import za.co.absa.abris.avro.read.confluent.SchemaManagerFactory
import za.co.absa.abris.avro.registry.SchemaSubject
import za.co.absa.abris.config.AbrisConfig
import ch.qos.logback.classic.{Level,Logger}
import org.slf4j.LoggerFactory
import java.nio.file.{Files, Paths}
import org.rogach.scallop._
import org.acalio.dm.model.avro.YComment
import java.{util => ju}
import io.acalio.utility.Utility._
import io.acalio.utility.Keys
import io.acalio.utility.sentiment._
import io.acalio.tasks._



class Conf(args: Seq[String]) extends ScallopConf(args) {

  // trait Formats
  lazy val inFormat = opt[String]("in-format", default = Some("kafka"), descr="type of the stream")
  lazy val outFormat = opt[String]("out-format", default = Some("console"), descr="type of the outputstream")

  // trait KafkaArguments 
  lazy val topic = opt[String]("topic", default=Some("mytopic"))
  lazy val broker = opt[String]("broker", default=Some("127.0.0.1:9092"))
  lazy val registry = opt[String]("registry", default=Some("http://localhost:8081"))
  lazy val offset = opt[String]("offset", default = Some("earliest"))

  lazy val fileSource = opt[String]("in-file", default=Some(""))
  lazy val fileSink = opt[String]("out-file", default=Some(""))

  //read the comments and generate the interaction graph
  lazy val interactionGraph = new Subcommand("interaction") //with KafkaArguments with AWSArguments with Formats

  //read from every available topic 
  lazy val sniffEntity = new Subcommand("sniff") {
    lazy val entity = opt[String]("entity", default=Some("comment"))
  }

  //add subcommand to the consumer
  addSubcommand(interactionGraph)
  addSubcommand(sniffEntity)
  verify
}

object Consumer  {

  case class KeyValuePair(key: String, value: String)

  def setProps(appProperties: ju.Properties, kvPairs: KeyValuePair*)  {
    kvPairs.foreach( x=> appProperties.put(x.key,x.value))
  }

  def main(args: Array[String]) = {
    setupLogging()
    val conf = new Conf(args)
    //configure comment arguments
    val appProperties = new ju.Properties;
    val inFormat = conf.inFormat.getOrElse("")
    val outFormat = conf.outFormat.getOrElse("")

    if(inFormat=="kafka"){
      setProps(appProperties,
        KeyValuePair(Keys.OFFSET, conf.offset.getOrElse("")),
        KeyValuePair(Keys.TOPIC, conf.topic.getOrElse("")),
        KeyValuePair(Keys.BROKER_URL, conf.broker.getOrElse("")),
        KeyValuePair(Keys.SCHEMA_REGISTRY_URL,conf.registry.getOrElse("")))
    } else if(inFormat=="csv") {
      setProps(appProperties,
        KeyValuePair(Keys.CSV_SOURCE, conf.fileSource.getOrElse("")))
    }

    //set the aws shink path
    if(outFormat=="csv") {
      setProps(appProperties,
        KeyValuePair(Keys.CSV_SINK, conf.fileSink.getOrElse("")))
    }

    //get the actual subcommand
    val subCommand = conf.subcommand
    var task: SparkTask = null
    subCommand match {
      case Some(conf.sniffEntity ) => {
        val entity =  conf.sniffEntity.entity.getOrElse("")
        task  = new Sniffer("sniffer", entity, outFormat, appProperties)

      }
      case Some(conf.interactionGraph) => {
        task = new InteractionGraph("ig", inFormat, outFormat, appProperties)
      }
      case _ => println("unrecognized option")
    }
    task.execute()
  }

}






