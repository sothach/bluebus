package bluebus.configuration

import java.net.URI
import java.time.Duration

import bluebus.authorization.SASAuthFactory

case class SBusConfig(rootUri: URI, queueName: String, sasKeyName: String, sasKey: String,
                       contentType: String = "application/json;charset=utf-8",
                       tokenLease: Duration = Duration.ofMinutes(1), 
                       timeout: Duration = Duration.ofSeconds(30)) {
  require(queueName.nonEmpty, "Queue name must be specified")
  val isSecure = rootUri.getScheme == "https"
  val endpoint = s"""$rootUri/$queueName/messages"""
  def sasToken = SASAuthFactory.createToken(endpoint, sasKeyName, sasKey, tokenLease)
}
object SBusConfig {
  def apply(config: Map[String, String]): SBusConfig = SBusConfig(
    rootUri=URI.create(config.getOrElse("root-uri","")),
    queueName=config("queue-name"),
    sasKeyName=config("sas-key-name"),
    sasKey=config("sas-key"),
    tokenLease=Duration.parse(config("token.ttl")),
    timeout=Duration.parse(config("read.timeout")))

}
