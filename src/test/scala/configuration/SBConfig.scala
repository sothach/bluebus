package configuration

object SBConfig {

  val config = Map[String,String](
    "poll-frequency" -> "PT20S",
    "root-uri" -> "127.0.0.1",
    "read.timeout" -> "PT30S",
    "token.ttl" -> "PT5M",
    "queue-name" -> "test",
    "sas-key-name" -> "sbuser",
    "sas-key" -> "12345")

}
