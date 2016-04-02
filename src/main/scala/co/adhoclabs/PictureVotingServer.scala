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
    val config = getClass.getResource("/config.yaml").getPath
    info(config)
    PictureHandler("https://s3.amazonaws.com/burner-mms/prod/bda7d5a3-d914-439c-9fa5-95800b0b8fa4.gif")
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[EventController]
      .add[ReportController]
  }
}
