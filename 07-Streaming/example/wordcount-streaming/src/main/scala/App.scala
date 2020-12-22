import org.rogach.scallop._
import java.{util => ju}


class Conf(args: Seq[String]) extends ScallopConf(args) {

  trait DataSource {_: ScallopConf =>
    lazy val url = opt[String]("url", required = true)
    lazy val port = opt[Int]("port", required = true)
  }

  lazy val wordsCommand = new Subcommand("wc") with DataSource 

  //add subcommand
  addSubcommand(wordsCommand)
  verify
}

object  App {

  def main(args: Array[String]) = {
    Utility.setupLogging()
    println("Starting")
    println("==============================")
    val conf = new Conf(args)
    val subCommand = conf.subcommand
    subCommand match {
      case Some(conf.wordsCommand) => {
        var p: ju.Properties  = new ju.Properties;
        p.put(WCParams.SOURCE_ADDRESS_KEY, conf.wordsCommand.url.getOrElse("localhost"))
        p.put(WCParams.SOURCE_PORT_KEY, conf.wordsCommand.port.getOrElse(9999))
        new WC(p).execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
