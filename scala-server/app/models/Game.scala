package models

import anorm._
import anorm.SqlParser._
import play.api.Logger
import play.api.Play.current
import play.api.db.DB
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Writes
import java.sql.Timestamp
import org.joda.time.LocalDateTime
import org.joda.time.DateTime
import java.util.Date

case class Game(id: Long, homeTeam: String, awayTeam: String, homeResult: String, awayResult: String, gameYear: String ,gameDate:String, gameDay:String , isRodadaAtual:Boolean)



object Game {
  
  implicit val gameJsonWriter = Json.writes[Game] 

  val simple = {
    get[Long]("id") ~
      get[String]("homeTeam") ~
      get[String]("awayTeam") ~
      get[String]("homeResult") ~
      get[String]("awayResult") ~
      get[String]("gameYear") ~
      get[String]("gameDate") ~
      get[String]("gameDay") map {
        case id ~ homeTeam ~ awayTeam ~ homeResult ~ awayResult ~ gameYear ~ gameDate ~ gameDay  => Game(id, homeTeam, awayTeam, homeResult, awayResult, gameYear, gameDate,gameDay,false)
      }
  }
  
  
  val currentRound = {
    get[Long]("id") ~
      get[String]("homeTeam") ~
      get[String]("awayTeam") ~
      get[String]("gameYear") ~
      get[String]("gameDate") ~
      get[String]("gameDay") ~
      get[Boolean]("isRodadaAtual") map {
        case id ~ homeTeam ~ awayTeam ~ gameYear ~ gameDate ~ gameDay ~ isRodadaAtual  => Game(id, homeTeam, awayTeam,  "","", gameYear, gameDate,gameDay,isRodadaAtual)
      } 
  }

  def findSpecific(homeTeam: String, awayTeam: String): List[Game] = DB.withConnection { 
    implicit c =>
       SQL("select  h.*,"+ 
    			"T1.teamName as homeTeam, " +
    			"T2.teamName as awayTeam " + 
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "INNER JOIN biblia_do_brasileirao.times AS T2 ON h.awayTeam_id = T2.id "  +
        "where  T1.teamName = {homeTeam} and T2.teamName = {awayTeam} " +
        "order by h.id")
      .on("homeTeam" -> (homeTeam.toUpperCase))
      .on("awayTeam" -> (awayTeam.toUpperCase))
      .as(simple *)
  }
  
  
   def findSpecificByYear(homeTeam: String, awayTeam: String,startYear: String, endYear: String): List[Game] = DB.withConnection { 
    implicit c =>
       SQL("select  h.*,"+ 
    			"T1.teamName as homeTeam, " +
    			"T2.teamName as awayTeam " + 
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "INNER JOIN biblia_do_brasileirao.times AS T2 ON h.awayTeam_id = T2.id "  +
        "where  T1.teamName = {homeTeam} and T2.teamName = {awayTeam} " +
        "and h.gameYear between {startYear} and {endYear} " + 
        "order by h.id")
      .on("homeTeam" -> (homeTeam.toUpperCase))
      .on("awayTeam" -> (awayTeam.toUpperCase))
      .on("startYear" -> startYear)
      .on("endYear" -> endYear)
      .as(simple *)
  }
  
   def findByYearRange(startYear: String, endYear: String): List[Game] = DB.withConnection { implicit c =>
    SQL("select  h.*,"+ 
    			"T1.teamName as homeTeam, " +
    			"T2.teamName as awayTeam " + 
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "INNER JOIN biblia_do_brasileirao.times AS T2 ON h.awayTeam_id = T2.id "  +
        "where h.gameYear between {startYear} and {endYear} " +
        "order by h.id")
      .on("startYear" -> startYear)
      .on("endYear" -> endYear)
      .as(simple *)

  }
   
   
   
   
   
   
   
   

   
  def findResultPerHomeTeamPerYearRange(startYear: String, endYear: String, team:String): List[Game] = DB.withConnection { implicit c =>
    SQL("select  h.*,"+ 
    			"T1.teamName as homeTeam, " +
    			"T2.teamName as awayTeam " + 
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "INNER JOIN biblia_do_brasileirao.times AS T2 ON h.awayTeam_id = T2.id "  +
        "where  T1.teamName = {team}  and h.gameYear between {startYear} and {endYear}" +
        "order by h.id")
      .on("startYear" -> startYear)
      .on("endYear" -> endYear)
      .on("team" -> (team.toUpperCase))
      .as(simple *)

  }

    def findResultPerAwayTeamPerYearRange(startYear: String, endYear: String, team:String): List[Game] = DB.withConnection { implicit c =>
    	SQL("select  h.*,"+ 
    			"T1.teamName as homeTeam, " +
    			"T2.teamName as awayTeam " + 
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "INNER JOIN biblia_do_brasileirao.times AS T2 ON h.awayTeam_id = T2.id "  +
        "where  T2.teamName = {team}  and h.gameYear between {startYear} and {endYear}" +
        "order by h.id")
      .on("startYear" -> startYear)
      .on("endYear" -> endYear)
      .on("team" -> (team.toUpperCase))
      .as(simple *)
  }
    
  def findResultPerHomeAndAwayTeamPerYearRange(startYear: String, endYear: String, team:String): List[Game] = DB.withConnection { implicit c =>
    SQL("select * from historico h where h.gameYear between {startYear} and {endYear}  and (h.homeTeam={team} or h.awayTeam = {team})")
      .on("startYear" -> startYear)
      .on("endYear" -> endYear)
      .on("team" -> (team.toUpperCase))
      .as(simple *)

  }
  
  
  def findCurrentRound(): List[Game] = DB.withConnection { implicit c =>
    SQL("select  r.id, r.gameYear, DATE_FORMAT(r.gameDate,'%d/%m/%Y %H:%i') as gameDate, r.gameDay, r.isRodadaAtual, "+ 
    			"T1.teamName as homeTeam, " +
    			"T2.teamName as awayTeam " + 
    	"from biblia_do_brasileirao.rodadas r "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON r.homeTeam_id = T1.id " + 
        "INNER JOIN biblia_do_brasileirao.times AS T2 ON r.awayTeam_id = T2.id " +
        "order by r.gameDay desc")
      .as(currentRound *)

  }
  

}

    
