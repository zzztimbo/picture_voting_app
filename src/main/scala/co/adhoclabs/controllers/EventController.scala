package co.adhoclabs.controllers

import co.adhoclabs.handlers.{PictureHandler, TextHandler}
import com.twitter.finatra.http.Controller

class EventController extends Controller {
  post("/event") { event: Event =>
    event match {
      case Event(_, payload) if event.isPicture() => PictureHandler(payload)
      case Event(_, payload) if event.isText() => TextHandler(payload)
    }
  }
}

case class Event(`type`: String, payload: String) {
  def isPicture(): Boolean = {
    val isInboundMedia = `type` == "inboundMedia"
    val isPictureFile = payload.isPictureFile
    isInboundMedia && isPictureFile
  }

  def isText(): Boolean = {
    `type` == "inboundText"
  }

  implicit class StringHelper(val str: String) {
    def isPictureFile: Boolean = {
      val extensions = Seq(".jpg", ".bmp", ".gif", ".png", ".jpeg")
      extensions.map(ext => str.endsWith(ext)).reduce((a, b) => a || b)
    }
  }
}

