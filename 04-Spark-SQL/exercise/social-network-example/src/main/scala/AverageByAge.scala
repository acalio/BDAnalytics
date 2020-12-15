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

    //convert the RDD into a Dataset[Person]
    val schemaPeople = people.toDS.cache
    //schemaPeople is an actual database table
    schemaPeople.printSchema()

    schemaPeople
      .groupBy("age")
        .agg(
          avg("numFriends"),
          sum("numFriends")
        ).show()
    
    spark.stop()
  }

}
