package controllers

import play.api.Play.current
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import models.Classificacao

object ClassificacaoController extends Controller {

  def getClassificacao() = Cached("getClassificacao", 3600) {
    Action {
      Ok(Json.toJson(Classificacao.getClassificacao()))
    }
  }

}