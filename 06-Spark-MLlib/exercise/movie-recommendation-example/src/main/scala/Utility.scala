import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.slf4j.LoggerFactory

object Utility {
  def setupLogging() {
    Logger.getLogger("org").setLevel(Level.ERROR)
  }

  def parseMovies (line: String): Option[Movie] = {
    val fields = line.split('|')
    if(fields.length>1)
      return Some(Movie(fields(0).toInt, fields(1)))
    else
      return None
  }

}
