import org.apache.spark._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.annotation.meta.field


class PopularHero(val graphPath: String, val namesPath: String)
extends Serializable {

  //Option is a way to say you can have or not have an option.
  //Some is a subclass of Option 
  def parseNames(line: String): Option[(Int, String)] =
  {
    var fields = line.split('\"')
    if(fields.length > 1) 
      return Some(fields(0).trim().toInt, fields(1))
    else
      return None //flat map will discard None value
  }

  def heroCoOccurences(line: String)  =
  {
    var fields = line.split(' ')
    (fields(0).toInt, fields.length-1)
  }

  def execute() {
  
    val sc = new SparkContext("local[*]", "PopularHero")

    val names = sc.textFile(namesPath)
    //flat map because there might be bad formatted lines
    val namesRDD = names.flatMap(parseNames) 


    val graph = sc.textFile(graphPath)

    //convert to <heroId, #of connections>
    val heroConnections = graph.map(heroCoOccurences)

    //in the case a hero appears on multiple lines
    val totalHeroConnections = heroConnections.reduceByKey((x,y) => (x+y))

    //flip the order of key/value
    val flipped = totalHeroConnections.map(x => (x._2, x._1))

    //get the hero with the most connection
    val mostPopular =  flipped.max()

    val heroName = namesRDD.lookup(mostPopular._2)

    println(s"$heroName")
    
  }


}


