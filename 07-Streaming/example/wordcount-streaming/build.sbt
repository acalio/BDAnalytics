ThisBuild / scalaVersion := "2.12.12"

lazy val scallop = "org.rogach" %% "scallop" % "3.5.1"
lazy val sql = "org.apache.spark" %% "spark-sql" % "3.0.0"

lazy val app = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "streaming word count",
    libraryDependencies ++= Seq(
      scallop,
      sql

    )
  )



