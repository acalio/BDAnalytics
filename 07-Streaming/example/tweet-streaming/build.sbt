ThisBuild / scalaVersion := "2.12.12"

val streamingTwitter = "org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0"
val sparkStreaming = "org.apache.spark" %% "spark-streaming" % "2.4.0"

lazy val twitter = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "Twitter Example",
    libraryDependencies ++= Seq(streamingTwitter, sparkStreaming)
  )




