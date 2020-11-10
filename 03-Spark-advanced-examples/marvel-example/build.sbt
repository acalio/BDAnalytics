import sbtassembly.MergeStrategy
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
ThisBuild/ scalaVersion := "2.12.10"

version := "1.0"
lazy val spark = "org.apache.spark" %% "spark-core" % "3.0.0"
lazy val scallop = "org.rogach" %% "scallop" % "3.5.1"
lazy val log = "ch.qos.logback" % "logback-classic" % "0.9.28"

lazy val app = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name  := "Marvel SuperHeros",

    libraryDependencies ++= Seq(
      (spark.
        exclude("org.mortbay.jetty", "servlet-api").
        exclude("commons-beanutils", "commons-beanutils-core").
        exclude("commons-collections", "commons-collections").
        exclude("commons-logging", "commons-logging").
        exclude("com.esotericsoftware.minlog", "minlog")),
      scallop,
      log
    )
  )

