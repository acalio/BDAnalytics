import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.clustering.StreamingKMeans
import org.apache.spark.sql.functions._
import java.{util => ju}
import org.apache.spark.sql.types.StructType
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.mllib.classification.StreamingLogisticRegressionWithSGD
import shapeless.labelled
import org.apache.spark.ml.feature.VectorAssembler

class KMeans(appConfig: ju.Properties) {


  def execute() {

    val spark = SparkSession
      .builder
      .appName("StreamingKMeans")
      .getOrCreate()

    import spark.implicits._
    val dataSchema = new StructType()
      .add("sepal-length", "double")
      .add("sepal-width", "double")
      .add("petal-length", "double")
      .add("petal-width", "double")
      .add("label", "string")

    //read from a socket
    val df = spark
      .readStream
      .option("sep", ",")
      .schema(dataSchema)
      .csv(appConfig.get(KMeansParams.PATH_KEY).toString)

    val collapsedDf = df
      .withColumn("features",
        array($"sepal-length",
          $"sepal-width",
          $"sepal-length",
          $"sepal-width"))
      .drop("sepal-width",
        "sepal-length",
        "petal-width",
        "petal-length").as[LabeledPoint]

    val features = 4
    val model = new StreamingLogisticRegressionWithSGD()
      .setInitialWeights(Vectors.zeros(features))

    model.trainOn(collapsedDf)

    val query = collapsedDf.writeStream
      .format("console")
      .outputMode("append")
      .option(s"checkpointLocation", "/tmp")
      .start()
      .awaitTermination()
  }

}
object KMeansParams {
  val PATH_KEY: String = "source.path"
}
