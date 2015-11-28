object Sample extends App {

  val busConfig = SBusConfig(
    rootUri=".servicebus.windows.net", 
    namespace="yourServiceNamspace",
    queueName="queueName",
    sasKeyName="RootManageSharedAccessKey", 
    sasKey="yourKey")
    
  val incomingService = new ServiceBusClient(busConfig)
  
  def handler = println  
  
  /** continually receive & handle messages from endpoint, until no more available */  
  def receiveMessages(): Unit =
    incomingService.receive map { message =>
        handler(message)
        receiveMessages(handler)
    }
  
  receiveMessages()
}
