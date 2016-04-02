package co.adhoclabs

import co.adhoclabs.controllers.{EventController, ReportController}
import co.adhoclabs.handlers.PictureHandler
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

object PictureVotingServerMain extends PictureVotingServer

class PictureVotingServer extends HttpServer {

  override def defaultFinatraHttpPort = ":9999"

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[EventController]
      .add[ReportController]
  }
}
