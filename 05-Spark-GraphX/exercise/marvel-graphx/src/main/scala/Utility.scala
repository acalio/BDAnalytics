import org.apache.spark.graphx._
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.slf4j.LoggerFactory

object Utility {
  def setupLogging() {
    Logger.getLogger("org").setLevel(Level.ERROR)
  }

  def makeVertices(line: String): Option[(VertexId, String)] = {
    var fields = line.split('\"')
    if(fields.length > 1){
      val heroID: Long = fields(0).trim().toLong
      if(heroID < 6847) //above that ID are not real character
        return Some(fields(0).trim().toLong, fields(1))
    }
    return None
  }

  def makeEdges(line: String): List[Edge[Int]] = {
    import scala.collection.mutable.ListBuffer
    var edges = new ListBuffer[Edge[Int]]()
    val fields = line.split(" ")
    val origin = fields(0)
    for(x<-1 to (fields.length -1))
      edges += Edge(origin.toLong, fields(x).toLong, 0)
    return edges.toList
  }


}
