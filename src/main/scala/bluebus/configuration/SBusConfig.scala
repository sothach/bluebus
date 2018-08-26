package bluebus.configuration

import java.net.URL
import java.time.Duration

import bluebus.authorization.SASAuthFactory

case class SBusConfig(rootUri: URL, queueName: String, sasKeyName: String, sasKey: String,
                       contentType: String = "application/json;charset=utf-8",
                       tokenLease: Duration = Duration.ofMinutes(1), 
                       timeout: Duration = Duration.ofSeconds(30)) {
  require(queueName.nonEmpty, "Queue name must be specified")
  val isSecure = rootUri.getProtocol == "https"
  val endpoint = s"""$rootUri/$queueName/messages"""
  def sasToken = SASAuthFactory.createToken(endpoint, sasKeyName, sasKey, tokenLease)
}
object SBusConfig {
  val url = new java.net.URL("https://aaa.bbb./xxx")

  println(s"protocol=${url.getProtocol}")
  val RootUri = "root-uri"
  val QueueName = "queue-name"
  val SasKeyName = "sas-key-name"
  val SasKey= "sas-key"
  val TokenTTL = "token-ttl"
  val ReadTimeout = "read-timeout"
  def apply(config: Map[String, String]): SBusConfig = SBusConfig(
    rootUri=new URL(config(RootUri)),
    queueName=config(QueueName),
    sasKeyName=config(SasKeyName),
    sasKey=config(SasKey),
    tokenLease=Duration.parse(config(TokenTTL)),
    timeout=Duration.parse(config(ReadTimeout)))

}
