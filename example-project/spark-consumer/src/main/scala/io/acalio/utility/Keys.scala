package io.acalio.utility

object Keys {
  val CSV_SOURCE: String = "s3.source"
  val CSV_SINK: String = "s3.sink"

  //kafka configuration
  val BROKER_URL: String = "kafka.bootstrap.servers"
  val TOPIC: String = "kafka.subscribe"
  val OFFSET: String= "kafka.startingOffsets"

  //schema registriy configuration
  val SCHEMA_REGISTRY_URL = "schemaRegistry.url"

  //application properties
  val ENTITY = "entity"

}
