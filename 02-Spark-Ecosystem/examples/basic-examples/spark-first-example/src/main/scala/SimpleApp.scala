/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession
import org.apache.log4j._


object SimpleApp {
  def main(args: Array[String]) {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val logFile = "/app/data/README.md" // Should be some file on your system
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()

    val logData = spark.read.textFile(logFile).cache()

    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}
