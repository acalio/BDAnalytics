import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.log4j._


/**
  * Instead of useing SQL instruction we can performs 
  *  operations directly over the dataframe. They are usually 
  *  more efficient than the .sql() method
  * 
  */
class Teen(val path: String, val version: Int) extends Serializable
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

    val lines = spark.sparkContext.textFile(path) // you hava a regular RDD of string objects
    val people = lines.map(mapper) // you have a regulard RDD of Person object

    import spark.implicits._ //remember to add this line

    //convert the RDD into a Dataset[Person]
    val schemaPeople = people.toDS.cache // you create an Dataset[Person]
    //schemaPeople is an actual database table
    schemaPeople.printSchema()

    if (version==0) { //use schemaPeople as an SQL table

      //register the dataset as a table called people
      schemaPeople.createOrReplaceTempView("people")
      //run an SQL query
      val teenagers = spark.sql("SELECT * FROM people WHERE age BETWEEN 13 AND 19")
      val results = teenagers.collect()
      results.foreach(println)

    } else {
      //use the no-sql vesion
      schemaPeople
        .filter(
          schemaPeople("age").between(13, 19)
        ).sort("age").show()
      //or
      schemaPeople.filter(col("age")>=13 && col("age")<=19).sort("age").show()
      schemaPeople.filter($"age">=13 && $"age"<19).sort("age").show(10) // $age == col(age)
    }
    spark.stop()
  }



}
