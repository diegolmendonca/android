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

case class Classificacao(id: Long, posicao: Int, teamName: String, pontos: Int, jogos: Int, vitorias: Int, empates: Int, derrotas: Int, golspro: Int, golscontra: Int, saldogols: Int)

object Classificacao {

  implicit val classificacaoJsonWriter = Json.writes[Classificacao]

  val simple = {
    get[Long]("id") ~
      get[Int]("posicao") ~
      get[String]("teamName") ~
      get[Int]("pontos") ~
      get[Int]("jogos") ~
      get[Int]("vitorias") ~
      get[Int]("empates") ~
      get[Int]("derrotas") ~
      get[Int]("golspro") ~
      get[Int]("golscontra") ~
      get[Int]("saldogols") map {
        case id ~ posicao ~ teamName ~ pontos ~ jogos ~ vitorias ~ empates ~ derrotas ~ golspro ~ golscontra ~ saldogols => Classificacao(id, posicao, teamName, pontos, jogos, vitorias, empates, derrotas, golspro, golscontra, saldogols)
      }
  }

  def getClassificacao(): List[Classificacao] = DB.withConnection {
    implicit c =>
      SQL("select * from classificacao c inner join times t on c.team_id = t.id order by c.posicao asc")
        .as(simple *)
  }

}

    
