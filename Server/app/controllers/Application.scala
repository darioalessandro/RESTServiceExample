package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def hiServer = Action { implicit request =>
    Ok(s"Hi ${request.remoteAddress}")
  }

  def echo(echo : String) = Action {
    Ok(echo)
  }

}