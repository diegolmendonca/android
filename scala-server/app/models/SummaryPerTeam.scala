package models

import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.db.DB
import anorm._
import anorm.SqlParser._

case class SummaryPerTeam(wins: Int, losses: Int, draws: Int, goalsMade: Int, goalsTaken: Int , winGames: List[Game], drawGames : List[Game], lossGames: List[Game])

object SummaryPerTeam {
  
  implicit val summaryPerTeamJsonWriter = Json.writes[SummaryPerTeam] 
  
}
  
  
