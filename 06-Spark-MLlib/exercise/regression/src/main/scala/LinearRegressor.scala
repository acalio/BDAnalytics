import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.ml.tuning.CrossValidator
import org.apache.spark.ml.feature.VectorAssembler

class LinearRegressor (
  val path: String,
  val maxIter: Int,
  val regularization: Double,
  val elasticNetParam: Double
) extends Serializable {



  def execute()  {
    val spark = SparkSession
      .builder()
      .appName("Linear Regression")
      .master("local[*]")
      .getOrCreate()
    
    import spark.implicits._
    val data = spark
      .sparkContext
      .textFile(path)
      .map(_.split(","))
      .map(x => {
        val y = x(0).toDouble
        var xs = new Array[Double](x.length-1)
        for(i <- 1 to x.length-1)
           xs(i-1) = x(i).toDouble
        (y, Vectors.dense(xs))
      })
    
    val colNames = Seq("y", "x")
    val df = data.toDF(colNames: _*)
    df.show
    // //split into Training and Test set
    val splittedDf = df.randomSplit(Array(.5, .5))
    val trainingDf = splittedDf(0)
    val testDf = splittedDf(1)

    val lir = new LinearRegression()
      .setRegParam(regularization) // regularization 
      .setElasticNetParam(elasticNetParam) // elastic net mixing
      .setMaxIter(maxIter) // max iteration
      .setFeaturesCol("x")
      .setLabelCol("y")

    val grid = new ParamGridBuilder()
      .addGrid(lir.regParam, Array(0.1, 0.5, 0.7))
      .addGrid(lir.maxIter, Array(10, 20))
      .build()

    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("y")
      .setPredictionCol("prediction")

    val cv = new CrossValidator()
      .setEstimator(lir)
      .setEvaluator(evaluator)
      .setEstimatorParamMaps(grid)

    val model = cv.fit(trainingDf)

    val predictions = model.transform(testDf)
    val predictionsTraining = model.transform(trainingDf)

    val rmse = evaluator.evaluate(predictions)
    val rmseTraining = evaluator.evaluate(predictionsTraining)
    println(s"Root-mean-squared error:\n\tTestSet: $rmse\n\tTrainingSet:$rmseTraining")

    spark.close()



  }

}
