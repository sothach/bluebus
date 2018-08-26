package configuration

import bluebus.configuration.SBusConfig

object SBConfig {
  val config = Map[String,String](
    SBusConfig.RootUri     -> "http://127.0.0.1",
    SBusConfig.ReadTimeout -> "PT30S",
    SBusConfig.TokenTTL    -> "PT5M",
    SBusConfig.QueueName   -> "test",
    SBusConfig.SasKeyName  -> "sbuser",
    SBusConfig.SasKey      -> "12345")

}
