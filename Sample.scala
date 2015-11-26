object Sample {

  val inboundBus = SBusConfig(
    rootUri=".servicebus.windows.net", 
    namespace="yourServiceNamspace",
    tokenLease=Duration.ofMinutes(5), 
    timeout=Duration.ofSeconds(30),
    queueName="queueName",
    sasKeyname="RootManageSharedAccessKey", 
    sasKey="yourKey")
    
  val incomingService = new ServiceBusClient(FDSConfig.inboundBus)
    
  def receiveMessages(client: ServiceBusClient, handler: (String) => Unit): Unit = {
    client.receive onComplete {
      case Success(message) =>
        receive(message)
        receiveMessages(client, handler)
      case Failure(t) =>
        Logger.warn(s"SB receive error: ${t.getMessage}")
    }
  }
  
  def test = receiveMessages(incomingService, (msg: String) => println(msg))
}
