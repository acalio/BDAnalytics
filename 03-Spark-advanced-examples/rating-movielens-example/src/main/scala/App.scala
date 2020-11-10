import org.rogach.scallop._
import org.apache.log4j.Logger
import org.apache.log4j.Level

class Conf(args: Seq[String]) extends ScallopConf(args) {
  trait ViewsPath {_:ScallopConf =>
    lazy val viewsPath = opt[String]("views", required = true)
  }

  trait MoviesPath {_:ScallopConf =>
    lazy val moviesPath = opt[String]("movies", required = true)
  }


  
  lazy val popularCommand = new Subcommand("popular") with ViewsPath

  lazy val mostPopularCommand = new Subcommand("most-popular") with ViewsPath with MoviesPath

  lazy val collaborativeFiltering = new Subcommand("collaborative") with ViewsPath with MoviesPath {
    lazy val movieId = opt[Int]("id", default = Some(0))
    lazy val scoreThreshold = opt[Double]("threshold", default = Some(.97))
    lazy val coOccurrences = opt[Long]("occurrences", default = Some(50))
  }

  //add subcommand
  addSubcommand(popularCommand)
  addSubcommand(mostPopularCommand)
  addSubcommand(collaborativeFiltering)
  verify
}

object  App {

  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    println("Starting")

    val conf = new Conf(args)
    val subCommand = conf.subcommand
    subCommand match {
      case Some(conf.popularCommand) => {
        val task = new MostPopular(conf.popularCommand.viewsPath.getOrElse(""))
        task.execute()
      }
      case Some(conf.mostPopularCommand) => {
        val task = new MostPopularBroadCast(
          conf.mostPopularCommand.viewsPath.getOrElse(""),
          conf.mostPopularCommand.moviesPath.getOrElse("")
        )
        task.execute()
      }
      case Some(conf.collaborativeFiltering) => {
        val task = new CollaborativeFiltering(
          conf.collaborativeFiltering.viewsPath.getOrElse(""),
          conf.collaborativeFiltering.moviesPath.getOrElse(""),
          conf.collaborativeFiltering.movieId.getOrElse(-1),
          conf.collaborativeFiltering.scoreThreshold.getOrElse(0.97),
          conf.collaborativeFiltering.coOccurrences.getOrElse(50)
        )
        task.execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
