ThisBuild / scalaVersion := "2.12.12"

val sparkStreaming = "org.apache.spark" %% "spark-streaming" % "3.0.1"
val kafkaSpark = "org.apache.spark" % "spark-streaming-kafka-0-10_2.12" % "3.0.1"



lazy val kafkaExample  = (project in file("."))
  .enablePlugins( JavaAppPackaging)
  .settings(
    name := "Kafka Example",
    libraryDependencies ++= Seq(sparkStreaming,kafkaSpark)
  )



