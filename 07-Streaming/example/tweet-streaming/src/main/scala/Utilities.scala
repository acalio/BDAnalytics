import org.apache.log4j.{Level, Logger}

object Utilities {
  def setupLogging() = {
    val rootLogger =Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)
  }

}
