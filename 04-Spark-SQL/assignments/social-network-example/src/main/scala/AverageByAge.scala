import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.log4j._


class AverageByAge(val path: String) extends Serializable
{

  def mapper(line: String): Person =
  {
    val fields = line.split(',')
    val person: Person = Person(fields(0).toInt, fields(1), fields(2).toInt, fields(3).toInt)
    return person
  }

  def execute() 
  {

    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    val lines = spark.sparkContext.textFile(path)
    val people = lines.map(mapper)

    import spark.implicits._ //remember to add this line

    //<---- your code here ---->
    //the number of friends for each age
    //the average of friends for each age
    
  }

}
