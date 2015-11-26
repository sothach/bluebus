import java.time.Duration

case class SBusConfig(rootUri: String, namespace: String, tokenLease: Duration, timeout: Duration,
                       queueName: String, sasKeyName: String, sasKey: String, 
                       contentType: String = "application/json;charset=utf-8") {
  val endpoint = s"https://$namespace$rootUri/$queueName/messages"
  def sasToken = SASAuthFactory.createToken(endpoint, sasKeyName, sasKey, tokenLease)
}
