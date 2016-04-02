package co.adhoclabs.handlers

import rapture.json._
import rapture.json.jsonBackends.jawn._

import scalaj.http.Http

trait PictureSetHandler extends DropboxHandler {
  val endpoint: String = s"$metadataEndpointUrl$folder/"

  def getPicturesSet() = {
    val meta = Http(endpoint)
      .headers(Seq("Authorization" -> s"Bearer $token"))
      .postForm(Seq()).asString

    val json = Json.parse(meta.body)
    json.contents.as[List[Json]]
      .map(_.path.as[String])
      .map(_.split("/").toList.last).toSet
  }
}
