import java.net.URI
import java.util.concurrent.Executors

import bluebus.client.ServiceBusClient
import bluebus.configuration.SBusConfig
import net.jadler.Jadler._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.concurrent.Waiters._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class SBClientSpec extends FlatSpec with Matchers with ScalaFutures with MockitoSugar with BeforeAndAfterAll {
  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))
  var testSubject: Option[ServiceBusClient] = None

  "when called" should "work" in {
    val payload = """{"key":"value}"""
    onRequest()
      .havingMethodEqualTo("DELETE")
      .havingPathEqualTo("/queueName/messages/head")
      .respond().withBody(payload).withStatus(200)
      .thenRespond().withStatus(204)

    val waiter = new Waiter

    val handler = (m: String) => {
      println(s"message=$m")
      m shouldBe payload
      waiter.dismiss
    }
    testSubject.isDefined shouldBe true
    val subject = testSubject.get

    def receiveMessages(f: String => Unit): Unit =
      subject.receive map { message =>
        f(message)
        receiveMessages(f)
      }

    receiveMessages(handler)
    waiter.await(timeout(2 seconds), dismissals(1))
  }

  override def beforeAll = {
    initJadler()
    testSubject = initializeClient
  }
  def initializeClient = {
    val config = SBusConfig(
      rootUri=URI.create(s"http://localhost:$port"),
      queueName="queueName",
      sasKeyName="RootManageSharedAccessKey",
      sasKey="sasKey")
    Some(new ServiceBusClient(config))
  }
  override def afterAll = {
    testSubject.foreach(_.shutdown())
    closeJadler()
  }
}
