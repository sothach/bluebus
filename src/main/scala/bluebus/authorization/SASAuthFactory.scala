package bluebus.authorization

import java.net.URLEncoder
import java.time.Duration

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

/** Shared Access Signature (SAS) to authenticate Service Bus clients, generated from the
  * HMAC-SHA256 of a resource string, consisting of the URI of the resource that is accessed
  * and an expiry timestamp, with the cryptographic key */
object SASAuthFactory {

  def createToken(targetUri: String, key: String, value: String, timeout: Duration) = {
    val calculateHmac = (data: String) => {
      val key = new SecretKeySpec(value.getBytes(), "HmacSHA256")
      val mac = Mac.getInstance(key.getAlgorithm)
      mac.init(key)
      val hmacBytes = mac.doFinal(data.getBytes())
      Base64.getEncoder.encodeToString(hmacBytes)
    }

    val encodedUri = URLEncoder.encode(targetUri.toLowerCase(), "UTF-8")
    val expiration = (System.currentTimeMillis() + timeout.toMillis) / 1000
    val hmac = URLEncoder.encode(calculateHmac(s"$encodedUri\n$expiration"), "UTF-8")

    s"SharedAccessSignature sig=$hmac&se=$expiration&skn=$key&sr=$encodedUri"
  }

}
