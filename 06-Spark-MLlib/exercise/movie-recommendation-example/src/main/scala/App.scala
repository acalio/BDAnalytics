import org.rogach.scallop._


class Conf(args: Seq[String]) extends ScallopConf(args) {
  trait ViewsPath {_:ScallopConf =>
    lazy val viewsPath = opt[String]("views", required = true)
  }

  trait MoviesPath {_:ScallopConf =>
    lazy val moviesPath = opt[String]("movies", required = true)
  }

  lazy val collaborativeFiltering = new Subcommand("collaborative") with ViewsPath with MoviesPath {
    lazy val userId = opt[Int]("id", default = Some(-1))
    lazy val rank = opt[Int]("rank", default = Some(8))
    lazy val numIterations = opt[Int]("iterations", default = Some(10))
  }

  //add subcommand
  addSubcommand(collaborativeFiltering)
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

      case Some(conf.collaborativeFiltering) => {
        val task = new MovieRecommendation(
          conf.collaborativeFiltering.viewsPath.getOrElse(""),
          conf.collaborativeFiltering.moviesPath.getOrElse(""),
          conf.collaborativeFiltering.userId.getOrElse(-1),
          conf.collaborativeFiltering.rank.getOrElse(8),
          conf.collaborativeFiltering.numIterations.getOrElse(10)
        )
        task.execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
