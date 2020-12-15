import org.rogach.scallop._


class Conf(args: Seq[String]) extends ScallopConf(args) {

  trait RegressionPath {_: ScallopConf =>
    lazy val regressionPath = opt[String]("data", required = true)
  }

  
  lazy val regressionCommand = new Subcommand("regression") with RegressionPath  {
    lazy val maxIter = opt[Int]("iterations", default=Some(10))
    lazy val regParam = opt[Double]("reg", default=Some(0.8))
    lazy val netParam = opt[Double]("net", default=Some(0.8))
  }



  //add subcommand
  addSubcommand(regressionCommand)
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
      case Some(conf.regressionCommand) => {
        val task = new LinearRegressor(
          conf.regressionCommand.regressionPath.getOrElse(""),
          conf.regressionCommand.maxIter.getOrElse(10),
          conf.regressionCommand.regParam.getOrElse(0.5),
          conf.regressionCommand.netParam.getOrElse(0.5)
        )
        task.execute()
      }
      case _ => println("unrecognized option")
    }
  }


}
