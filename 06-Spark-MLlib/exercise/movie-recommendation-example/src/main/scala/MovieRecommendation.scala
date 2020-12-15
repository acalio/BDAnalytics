import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec

import org.apache.spark.ml.recommendation.ALS


import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.sql.SparkSession
import scala.concurrent.duration.span


class MovieRecommendation(
  val viewsPath: String,
  val moviesPath: String,
  val movieId: Int, 
  val rank: Int, // rank of the features matrix
  val numIterations: Int  //number of iterations
) 
extends Serializable {
  def execute() {
    val spark = SparkSession
      .builder()
      .appName("MovieRecommendationASL")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._ //remember to add this line
    print(s"reading: $viewsPath")
    val lines = spark.sparkContext.textFile(viewsPath)
    val ratings = lines.map(x => {
      val lineSplitted = x.split("\t")
      MovieRating(lineSplitted(0).toInt, //user Id
        lineSplitted(1).toInt, //movie Id
        lineSplitted(2).toInt) // rating
      }).toDS()
    ratings.printSchema()
    
   val moviesDf = spark
      .sparkContext
      .textFile(moviesPath)
      .flatMap(Utility.parseMovies).toDS()

    moviesDf.printSchema()

    //split into Training and Test set
    val splittedDf = ratings.randomSplit(Array(.5, .5))
    val trainingDf = splittedDf(0)
    val testDf = splittedDf(1)

    trainingDf.printSchema()

    
    //training the recommendation model on the training set
    println("Start the model training")

    val als = new ALS()
       .setMaxIter(numIterations)
       .setRank(rank)
       .setUserCol("userId")
       .setItemCol("movieId")
       .setRatingCol("rating")

    val model = als.fit(trainingDf)

    // Evaluate the model by computing the RMSE on the trianing and test set data 
    // Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
    model.setColdStartStrategy("drop")
    //the transform method will add a colum to the original dataset with the predictions
    //obtained by the model we have just trained
    val predicitons = model.transform(testDf)
    val predicitonTraining = model.transform(trainingDf)


    //compute the root means sqared error between the vector of prediction
    //and the actual ratings porvided by the users
    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("rating")
      .setPredictionCol("prediction")

    val rmse = evaluator.evaluate(predicitons)
    val rmseTraining = evaluator.evaluate(predicitonTraining)
    println(s"Root-mean-squared error:\n\tTestSet: $rmse\n\tTrainingSet:$rmseTraining")

    // Generate top 10 movie recommendations for each user
    val userRecs = model.recommendForAllUsers(10)
    // Generate top 10 user recommendations for each movie
    val movieRecs = model.recommendForAllItems(10)
 
    //get a subste of users
    val users = ratings.select(als.getUserCol).distinct().limit(3)
    //get recommendations
    val userSubsetRecs = model.recommendForUserSubset(users, 10)

    // Generate top 10 user recommendations for a specified set of movies
    val movies = ratings.select(als.getItemCol).distinct().limit(3)
    val movieSubSetRecs = model.recommendForItemSubset(movies, 10)

    //join to get the movi name
    userRecs.show()
    movieRecs.show()
    userSubsetRecs.show()
    movieSubSetRecs.show()
    spark.stop()

  }

}
