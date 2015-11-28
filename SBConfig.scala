import java.time.Duration

case class SBusConfig(rootUri: String, namespace: String,
                       queueName: String, sasKeyName: String, sasKey: String, 
                       contentType: String = "application/json;charset=utf-8",
                       tokenLease: Duration = Duration.ofMinutes(1), 
                       timeout: Duration = Duration.ofSeconds(30)) {
  val endpoint = s"https://$namespace$rootUri/$queueName/messages"
  def sasToken = SASAuthFactory.createToken(endpoint, sasKeyName, sasKey, tokenLease)
}
