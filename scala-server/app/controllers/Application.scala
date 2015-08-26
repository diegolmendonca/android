package controllers

import models.Game
import models.SummaryPerTeam
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import scala.collection.mutable.ListBuffer
import builder.SummaryResultBuilder
import play.api.cache.Cached
import play.api.Play.current
import models.Game


object Application extends Controller {

  def getGameStatistics(homeTeam: String, awayTeam: String) = Cached(homeTeam + awayTeam, 3600) {
    Action {
      Ok(Json.toJson(Game.findSpecific(homeTeam, awayTeam)))
    }
  }
  
    def getGameStatisticsByYear(homeTeam: String, awayTeam: String ,startYear: String, endYear: String) = Cached(homeTeam + awayTeam + startYear + endYear, 3600) {
    Action {
      Ok(Json.toJson(Game.findSpecificByYear(homeTeam, awayTeam, startYear, endYear)))
    }
  }


  def getGameByYearRange(startYear: String, endYear: String) = Cached(startYear + endYear, 3600) {
    Action {
      Ok(Json.toJson(Game.findByYearRange(startYear, endYear)))
    }
  }

  def findResultPerHomeTeamPerYearRange(startYear: String, endYear: String, team: String) = Cached(startYear + endYear + team + "home", 3600) {
    Action {
      val summaryResultBuilder = new SummaryResultBuilder
      Ok(Json.toJson(summaryResultBuilder.calculateSummary(Game.findResultPerHomeTeamPerYearRange(startYear, endYear, team))))
    }
  }

  def findResultPerAwayTeamPerYearRange(startYear: String, endYear: String, team: String) = Cached(startYear + endYear + team + "away", 3600) {
    Action {
      val summaryResultBuilder = new SummaryResultBuilder
      val resultAway = summaryResultBuilder.calculateSummary(Game.findResultPerAwayTeamPerYearRange(startYear, endYear, team))

      // sumWins nesse caso eh de losses e vice-versa
      //goalsMade eh goalsTake e vice-versa
      // winIds eh lossIds e vice-versa

      Ok(Json.toJson(SummaryPerTeam(resultAway.losses,
        resultAway.wins,
        resultAway.draws,
        resultAway.goalsTaken,
        resultAway.goalsMade,
        resultAway.lossGames.toList,
        resultAway.drawGames.toList,
        resultAway.winGames.toList)))
    }
  
  }


  def findResultPerHomeAndAwayTeamPerYearRange(startYear: String, endYear: String, team: String) = Cached(startYear + endYear + team + "homeAway", 3600) {
    Action {

      val summaryResultBuilder = new SummaryResultBuilder

      val resultAway = summaryResultBuilder.calculateSummary(Game.findResultPerAwayTeamPerYearRange(startYear, endYear, team))
      val resultHome = summaryResultBuilder.calculateSummary(Game.findResultPerHomeTeamPerYearRange(startYear, endYear, team))

      Ok(Json.toJson(SummaryPerTeam(resultHome.wins + resultAway.losses,
        resultHome.losses + resultAway.wins,
        resultHome.draws + resultAway.draws,
        resultHome.goalsMade + resultAway.goalsTaken,
        resultHome.goalsTaken + resultAway.goalsMade,
        resultHome.winGames.toList ::: resultAway.lossGames.toList,
        resultHome.drawGames.toList ::: resultAway.drawGames.toList,
        resultHome.lossGames.toList ::: resultAway.winGames.toList)))
    }
  }
  
}