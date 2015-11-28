object Sample {

  val busConfig = SBusConfig(
    rootUri=".servicebus.windows.net", 
    namespace="yourServiceNamspace",
    queueName="queueName",
    sasKeyName="RootManageSharedAccessKey", 
    sasKey="yourKey=")
    
  val incomingService = new ServiceBusClient(busConfig)
    
  /** recursively receive & handle messages from endpoint until no more available */  
  def receiveMessages(handler: (String) => Unit): Unit =
    incomingService.receive map { message =>
        handler(message)
        receiveMessages(client, handler)
    }
  
  receiveMessages(incomingService, (msg: String) => println(msg))
}
