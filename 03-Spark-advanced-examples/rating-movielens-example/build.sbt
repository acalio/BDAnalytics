ThisBuild/ scalaVersion := "2.12.10"
version := "1.0"

lazy val spark = "org.apache.spark" %% "spark-core" % "3.0.0"
lazy val scallop = "org.rogach" %% "scallop" % "3.5.1"
lazy val log = "ch.qos.logback" % "logback-classic" % "0.9.28"

lazy val app = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name  := "MovieLens",
    libraryDependencies ++= Seq(
      spark,
      scallop,
      log
    )
  )

