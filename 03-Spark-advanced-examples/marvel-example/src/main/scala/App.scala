import org.apache.spark._
import org.rogach.scallop._
import org.apache.log4j.Logger
import org.apache.log4j.Level

class Conf(args: Seq[String]) extends ScallopConf(args) {
  trait CommonArguments {_:ScallopConf =>
    lazy val path = opt[String]("path", required = true)
  }

  lazy val separationCommand = new Subcommand("separation") with CommonArguments {
    lazy val startingCharacterId = opt[Long]("source", default = Some(5306L))
    lazy val targetCharacterId = opt[Long]("target", default = Some(14L))
  }

  lazy val popularCommand = new Subcommand("popular") with CommonArguments  {
    lazy val namesPath = opt[String]("names",  required=true)
  }

  //add subcommand
  addSubcommand(popularCommand)
  addSubcommand(separationCommand)
  verify
}

object  App {

  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    println("Starting")
    println(args.toString())
    val conf = new Conf(args)
    println(conf.subcommand)
    val subCommand = conf.subcommand
    print(subCommand)
    subCommand match {
      case Some(conf.separationCommand) => {
        val ds = new DegreeSeparation(
          conf.separationCommand.path.getOrElse(""),
          conf.separationCommand.startingCharacterId.getOrElse(0L),
          conf.separationCommand.targetCharacterId.getOrElse(0L)
        )
        ds.execute()
      }
      case Some(conf.popularCommand) => {

        val ph = new PopularHero(
          conf.popularCommand.path.getOrElse(""),
          conf.popularCommand.namesPath.getOrElse("")
        )
        ph.execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
