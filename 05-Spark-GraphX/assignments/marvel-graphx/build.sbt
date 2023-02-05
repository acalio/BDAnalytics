ThisBuild / scalaVersion := "2.12.12"

lazy val graphX = "org.apache.spark" %% "spark-graphx" % "3.0.0"
lazy val scallop = "org.rogach" %% "scallop" % "3.5.1"

lazy val app = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "Marvel-GraphX",
    libraryDependencies ++= Seq(
      graphX,
      scallop
    )
  )


