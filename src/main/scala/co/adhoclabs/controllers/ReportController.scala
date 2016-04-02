package co.adhoclabs.controllers

import co.adhoclabs.stateful.PictureVoteCounter
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import rapture.json._
import rapture.json.jsonBackends.jawn._

class ReportController extends Controller {
  get("/report") { request: Request =>
    Json(PictureVoteCounter.getVotes).toString()
  }
}