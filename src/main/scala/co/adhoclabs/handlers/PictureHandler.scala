package co.adhoclabs.handlers

import scalaj.http._
import co.adhoclabs.util.NameGenerator.generateName

class PictureHandler(val pictureFile: String) extends DropboxHandler {

  val pictureExtension = s".${pictureFile.split("\\.").toList.last}"
  val endpoint: String = s"$saveEndpointUrl$folder/${generateName()}${pictureExtension}"

  def uploadPicture(): Unit = {
    Http(endpoint)
      .headers(Seq("Authorization" -> s"Bearer $token"))
      .postForm(Seq("url" -> pictureFile)).asString
  }
}

object PictureHandler {
  def apply(pictureFile: String) = new PictureHandler(pictureFile).uploadPicture()
}
