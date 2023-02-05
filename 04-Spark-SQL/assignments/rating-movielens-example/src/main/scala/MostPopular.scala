import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

class MostPopular(
  val path: String,
  val names: String) extends Serializable
{



  def execute()
  {
    val spark = SparkSession
      .builder()
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    //infer the schema directly from the Dataset and register the Dataset as a table
    import spark.implicits._ //remember to add this line
    print(s"reading: $path")
    val lines = spark.sparkContext.textFile(path)
    val movies = lines.map(x => {
      val lineSplitted = x.split("\t")
      MovieRating(lineSplitted(0).toInt, //user Id
        lineSplitted(1).toInt, //movie Id
        lineSplitted(2).toInt) //
      }).toDS()
    movies.printSchema()
    
    val movieNames = spark
      .sparkContext
      .textFile(names)
      .flatMap(Utility.parseMovies).toDS()

    movieNames.printSchema()
    movieNames.show()
    
    val topMovies = movies
      .groupBy("movieId")
      .count()
      .orderBy(desc("count")).cache()

    //make a join to get the names of the movies
    topMovies.as("left")
      .join(movieNames.as("right"), $"left.movieId"===$"right.movieId")
      .drop($"right.movieId")
      .show()
  }

}


