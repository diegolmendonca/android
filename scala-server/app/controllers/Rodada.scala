package controllers

import models.BrasileiraoStatis
import play.api.Play.current
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import models.Game

object Rodada extends Controller {

  def getCurrentRound() = Cached("CurrentRound", 3600) {
    
    Action {
     Ok(Json.toJson(Game.findCurrentRound()))
    }

  }
  

}