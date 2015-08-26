package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.Logger

case class BrasileiraoStatis(var id: Long,  totalGame: Long, team: String , var size : Long , var homeResult : Double, var awayResult : Double)

  
  
object BrasileiraoStatis {
  
  implicit val brasileiraoStatisJsonWriter = Json.writes[BrasileiraoStatis]

   val simpleHome = {
    get[Long]("totalGame") ~
    get[String]("homeTeam")  map {
    	case totalGame ~ homeTeam   => BrasileiraoStatis(0, totalGame, homeTeam, 0 , 0,0)
      }
  }
  
  
  val simpleAway = {
    get[Long]("totalGame") ~
    get[String]("awayTeam")  map {
    	case totalGame ~ homeTeam   => BrasileiraoStatis(0, totalGame, homeTeam, 0 , 0,0)
      }
  }
  
  
  val simpleGoalsHome = {
    get[String]("homeTeam")~
    get[Double]("homeResult") ~
    get[Double]("awayResult") map {
    	case  homeTeam ~ homeResult ~ awayResult => BrasileiraoStatis(0, 0, homeTeam, 0 , homeResult,awayResult)
      }
  }
  
  
  val simpleGoalsAway = {
    get[String]("awayTeam") ~
    get[Double]("homeResult") ~
    get[Double]("awayResult") map {
    	case  homeTeam ~ homeResult ~ awayResult => BrasileiraoStatis(0, 0, homeTeam, 0 , homeResult,awayResult)
      }
  }

  def getNumberOfGamesBrasileiraoStats(kind: String): List[BrasileiraoStatis] = DB.withConnection {
    implicit c =>
      if (kind.equals("homeTeam")) 
        
        SQL("select  count(*) as totalGame,"+ 
    			"T1.teamName as homeTeam " +
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "group by T1.teamName " + 
        "order by totalGame desc").as(simpleHome *)
        
      else 
                
        SQL("select  count(*) as totalGame,"+ 
    			"T1.teamName as awayTeam " +
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.awayTeam_id = T1.id " + 
        "group by T1.teamName " + 
        "order by totalGame desc").as(simpleAway *)

  }
  
  
  
  def getNumberOfGoalsBrasileiraoStats(where: String , what:String): List[BrasileiraoStatis] = DB.withConnection {
    implicit c =>
      if (where.equals("homeTeam")  && what.equals("pro")) 
        
        SQL("select  sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult, "+ 
    			"T1.teamName as homeTeam " +
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "group by T1.teamName " + 
        "order by homeResult desc").as(simpleGoalsHome *)
        
      else if (where.equals("homeTeam")  && what.equals("taken")) 
        
        SQL("select  sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult, "+ 
    			"T1.teamName as homeTeam " +
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.homeTeam_id = T1.id " + 
        "group by T1.teamName " + 
        "order by awayResult desc").as(simpleGoalsHome *)
      
      else if (where.equals("awayTeam")  && what.equals("pro")) 
                
        SQL("select sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult,"+ 
    			"T1.teamName as awayTeam " +
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.awayTeam_id = T1.id " + 
        "group by T1.teamName " + 
        "order by awayResult desc").as(simpleGoalsAway *)
        
        
      else   
        
        SQL("select sum(h.homeResult) as homeResult, sum(h.awayResult) as awayResult,"+ 
    			"T1.teamName as awayTeam " +
    	"from biblia_do_brasileirao.historico h "  +
        "INNER JOIN biblia_do_brasileirao.times AS T1 ON h.awayTeam_id = T1.id " + 
        "group by T1.teamName " + 
        "order by homeResult desc").as(simpleGoalsAway *)

  }
  
  
  
  
  
  
  
}




  
  
  
  
