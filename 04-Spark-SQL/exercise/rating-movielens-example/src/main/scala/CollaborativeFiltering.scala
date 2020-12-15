import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
/**
  * Implement a collaborative filtering algorithm
  * using Spark SQL
  * 
  */
class CollaborativeFiltering(
  val viewsPath: String,
  val moviesPath: String,
  val movieId: Int,
  val scoreThreshold: Double,
  val occurenceThreshold: Long,
)
extends Serializable {


  def execute() {
    val spark = SparkSession
      .builder()
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._ //remember to add this line
    print(s"reading: $viewsPath")
    val lines = spark.sparkContext.textFile(viewsPath)
    val ratings = lines.map(x => {
      val lineSplitted = x.split("\t")
      MovieRating(lineSplitted(0).toInt, //user Id
        lineSplitted(1).toInt, //movie Id
        lineSplitted(2).toInt) //
      }).toDS()
    ratings.printSchema()
    
   val movies = spark
      .sparkContext
      .textFile(moviesPath)
      .flatMap(Utility.parseMovies).toDS()
    movies.printSchema()
    //start collaborative filtering
    //join the rating table so to have the following structure
    // | userId | movie1 | r1 | movie2 | r2 |
    val joinedRatings = ratings
      .join(
        ratings
          .as("right")
          .withColumnRenamed("movieId", "m2")
          .withColumnRenamed("rating", "r2"),
        "userId")
      .withColumnRenamed("movieId", "m1")
      .withColumnRenamed("rating", "r1")
      .filter($"m1"<$"m2")  //remove duplicate entries
      .cache() //cache the result since we are going to reuse this dataset

    val moviePairRatings = joinedRatings
      .withColumn("combinedRating", $"r1"*$"r2") //prepare data to compute the scalar product
      .withColumn("norm1", $"r1"*$"r1")
      .withColumn("norm2", $"r2"*$"r2")
      .groupBy("m1","m2")
      .agg(
        sum("combinedRating").as("numerator"),
        count("combinedRating").as("support"),
        sum("norm1").as("norm1"),
        sum("norm2").as("norm2")
      )
      .filter($"support">occurenceThreshold) //cast out irrelevant entries
      .withColumn("normProduct", sqrt($"norm1")*sqrt($"norm2"))
      .withColumn("similarity", $"numerator"/$"normProduct")
      .join(movies.as("left"), $"left.movieId"===$"m1")
      .join(movies.as("left_"), $"left_.movieId"===$"m2")
      .drop("norm1", "norm2", "numerator", "normProduct","movieId")

    if(movieId<0) {
      moviePairRatings.sort($"similarity".desc).show()
    } else {
      val similarMovies = moviePairRatings
        .filter($"m1"===movieId || $"m2"===movieId).sort($"similarity".desc,$"support".desc).show(50,false)
    }


  }

}
