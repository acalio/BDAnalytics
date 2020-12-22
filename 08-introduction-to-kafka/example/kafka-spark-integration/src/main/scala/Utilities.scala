import java.util.regex.Pattern
import org.apache.log4j._

object Utilities {
  def setupLogging() = {
    val rootLogger =Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)
  }

  def  apacheLogParser(): Pattern  = {
    val ddd = "\\d{1,2}"
    val ip = s"($ddd\\.$ddd\\.$ddd\\.$ddd)?"
    val client = "(\\S+)"
    val user = "(\\S+)"
    val dateTime = "(\\[.+?\\])"
    val request = "\"(.*?)\""
    val status = "(\\d{3})"
    val bytes = "(\\S+)"
    val referer = "\"(.*?)\""
    val agent = "\"(.*?)\""
    val regex = s"$ip $client $user $dateTime $request $status $bytes $referer $agent"
    Pattern.compile(regex)
  }

}
