import org.rogach.scallop._
import org.apache.log4j.Logger
import org.apache.log4j.Level

class Conf(args: Seq[String]) extends ScallopConf(args) {
  trait Path {_:ScallopConf =>
    lazy val path = opt[String]("path", required = true)
  }
  
  lazy val teen = new Subcommand("teenager") with Path {
    lazy val version = opt[Int]("version", default = Some(0))
  }

  lazy val avg = new Subcommand("average") with Path
  //add subcommand
  addSubcommand(teen)
  addSubcommand(avg)
  verify
}

object  App {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    println("Starting")
    val conf = new Conf(args)
    val subCommand = conf.subcommand
    subCommand match {
      case Some(conf.teen) => {
        new Teen(conf.teen.path.getOrElse(""), conf.teen.version.getOrElse(0)).execute()
      }
      case Some(conf.avg) => {
        new AverageByAge(conf.avg.path.getOrElse("")).execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
