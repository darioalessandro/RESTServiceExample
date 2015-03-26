package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def hiServer = Action { implicit request =>
    Ok(s"Hi ${request.headers.get("User-Agent")}")
  }

  def echo = Action (parse.urlFormEncoded) { implicit request =>
    Ok(request.body.getOrElse("textToEcho","You're missing the textToEcho parameter").toString)
  }

}