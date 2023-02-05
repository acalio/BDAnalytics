import org.rogach.scallop._
import java.{util => ju}


class Conf(args: Seq[String]) extends ScallopConf(args) {

  trait DataSource {_: ScallopConf =>
    lazy val path = opt[String]("path", required = true)
  }

  lazy val kmeansCommand = new Subcommand("kmeans") with DataSource 

  //add subcommand
  addSubcommand(kmeansCommand)
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
      case Some(conf.kmeansCommand) => {
        var p: ju.Properties  = new ju.Properties;
        p.put(KMeansParams.PATH_KEY, conf.kmeansCommand.path.getOrElse(""))
        new KMeans(p).execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
