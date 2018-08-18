import java.net.URI

import bluebus.client.ServiceBusClient
import bluebus.configuration.SBusConfig
import org.scalatest.{FlatSpec, Matchers}

class SecureClientSpec extends FlatSpec with Matchers {

  "when the client is configured with a secure endpoint, it" should
    "return the expected endpoint" in {
    val config = SBusConfig(
      rootUri=URI.create(s"https://localhost:443"),
      queueName="queueName",
      sasKeyName="RootManageSharedAccessKey",
      sasKey="sasKey")
    val subject = new ServiceBusClient(config)

    subject.peek

  }

}
