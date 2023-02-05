import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.slf4j.LoggerFactory

object Utility {
  def setupLogging() {
    Logger.getLogger("org").setLevel(Level.ERROR)
  }


}
