package controllers

import models.BrasileiraoStatis
import play.api.Play.current
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller

object BrasileiraoData extends Controller {

  def getBrasileiraoHomeTeamGames(team: String) = Cached("BrasileiraoHomeTeamGames" + team, 3600) {
    Action {
      Ok(Json.toJson(buildListOfBrasileiraoStatis(BrasileiraoStatis.getNumberOfGamesBrasileiraoStats("homeTeam"), team)))
    }

  }

  def getBrasileiraoAwayTeamGames(team: String) = Cached("BrasileiraoAwayTeamGames" + team, 3600) {
    Action {
      Ok(Json.toJson(buildListOfBrasileiraoStatis(BrasileiraoStatis.getNumberOfGamesBrasileiraoStats("awayTeam"), team)))
    }
  }
  
  
   def getBrasileiraoHomeTeamProGoals(team: String) = Cached("BrasileiraoHomeTeamProGoals" + team, 3600) {
    Action {
      Ok(Json.toJson(buildListOfBrasileiraoStatis(BrasileiraoStatis.getNumberOfGoalsBrasileiraoStats("homeTeam", "pro"), team)))
    }

  }
   
   def getBrasileiraoHomeTeamTakenGoals(team: String) = Cached("BrasileiraoHomeTeamTakenGoals" + team, 3600) {
    Action {
      Ok(Json.toJson(buildListOfBrasileiraoStatis(BrasileiraoStatis.getNumberOfGoalsBrasileiraoStats("homeTeam", "taken"), team)))
    }

  }

  def getBrasileiraoAwayTeamProGoals(team: String) = Cached("BrasileiraoAwayTeamProGoals" + team, 3600) {
    Action {
      Ok(Json.toJson(buildListOfBrasileiraoStatis(BrasileiraoStatis.getNumberOfGoalsBrasileiraoStats("awayTeam" , "pro"), team)))
    }
  }
  
   def getBrasileiraoAwayTeamTakenGoals(team: String) = Cached("BrasileiraoAwayTeamTakenGoals" + team, 3600) {
    Action {
      Ok(Json.toJson(buildListOfBrasileiraoStatis(BrasileiraoStatis.getNumberOfGoalsBrasileiraoStats("awayTeam" , "taken"), team)))
    }
  }
  
  def buildListOfBrasileiraoStatis(listOfGames: List[BrasileiraoStatis], team: String): List[BrasileiraoStatis] = {

    val listLength = listOfGames.length
    val listWithIndex = listOfGames.zipWithIndex.filter { case (game, index) => index == 0 || game.team == team.toUpperCase }

    val (firstTeam, positionFirst) = listWithIndex.head
    
    
    val (specificTeam, positionSpecific) = if (listWithIndex.size == 1)  (firstTeam, positionFirst) else  listWithIndex(1)
    



    firstTeam.id = positionFirst + 1
    firstTeam.size = listLength

    specificTeam.id = positionSpecific + 1
    specificTeam.size = listLength

    List(firstTeam, specificTeam)
  }
  
  
  
  

}