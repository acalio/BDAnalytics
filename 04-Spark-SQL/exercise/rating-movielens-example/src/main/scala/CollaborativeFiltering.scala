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

    //<! ------ You Code Here  ---- >


    if(movieId<0) {
      moviePairRatings.sort($"similarity".desc).show()
    } else {
      val similarMovies = moviePairRatings
        .filter($"m1"===movieId || $"m2"===movieId).sort($"similarity".desc,$"support".desc).show(50,false)
    }


  }

}
