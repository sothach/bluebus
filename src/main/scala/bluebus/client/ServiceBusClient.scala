package bluebus.client

import bluebus.configuration.SBusConfig
import dispatch.{Http, Req, as, url}

import scala.concurrent.ExecutionContext.Implicits.global

class ServiceBusClient(config: SBusConfig) {
  val http = Http.default
  def readHeaders = Map(
    "Authorization" -> config.sasToken,
    "Accept" -> config.contentType)
println(s"url=[${config.endpoint}/head]")
  val headRequest = {
    val req = (url(config.endpoint) / "head").timeout
    config.isSecure match {
      case true =>
        req.secure
      case false =>
        req
    }
  }

  def peek = {
    http(headRequest.POST <:< readHeaders << "" OK as.String)
  }

  def receive = {
    http(headRequest.DELETE <:< readHeaders << "" OK as.String)
  }

  def send(message: String, messageId: String) = {
    val headers = Map(
      "MessageId" -> messageId,
      "Authorization" -> config.sasToken,
      "Content-Type" -> config.contentType)
    http(headRequest.POST <:< headers << message OK as.String)
  }

  def shutdown() = http.shutdown()

  private implicit class Request(req: Req) {
    def timeout = req.setParameters(Map(
      "timeout" -> Seq(config.timeout.getSeconds.toString)))
  }
}
