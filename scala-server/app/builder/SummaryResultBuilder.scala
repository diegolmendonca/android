package builder

import scala.collection.mutable.ListBuffer
import models.SummaryPerTeam
import models.Game

class SummaryResultBuilder {

  def calculateSummary(result: List[models.Game]): SummaryPerTeam = {

    var sumWins = 0
    var sumLosses = 0
    var sumDraws = 0
    var goalsMade = 0
    var goalsTaken = 0

    var lossGames = new ListBuffer[Game]()
    var winGames = new ListBuffer[Game]()
    var drawGames = new ListBuffer[Game]()

    var sum = 0;

    //    val (wins, others) = result.partition(a => a.homeResult > a.awayResult)
    //    val (losses, draws) = others.partition(a => a.homeResult < a.awayResult)
    //    val goalsMade = result.foldLeft(0) {  (total, n) => total + n.homeResult.toInt }
    //    val goalsTaken = result.foldLeft(0) {  (total, n) => total + n.awayResult.toInt }

    result.foreach(

      x => {

        val resultHome = x.homeResult.toInt
        val resultAway = x.awayResult.toInt

        goalsMade += resultHome
        goalsTaken += resultAway

        resultHome match {
          case y if y < resultAway =>
            {
              sumLosses = sumLosses + 1
              lossGames += x
            }

          case y if y > resultAway =>
            {
              sumWins = sumWins + 1
              winGames += x

            }
          case _ => {
            sumDraws = sumDraws + 1
            drawGames += x
          }
        }
      })
    SummaryPerTeam(sumWins, sumLosses, sumDraws, goalsMade, goalsTaken, winGames.toList, drawGames.toList, lossGames.toList)
  }
  
  

}