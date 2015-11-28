import java.net.URLEncoder
import java.util.Date
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

import java.time.Duration

object SASAuthFactory {
  val HMAC_SHA256_ALG = "HmacSHA256"
  val utf8 = "UTF-8"

  def createToken(targetUri: String, key: String, value: String, timeout: Duration) = {
    def calculateHmac(data: String) = {
      val key = new SecretKeySpec(value.getBytes(), HMAC_SHA256_ALG)
      val mac = Mac.getInstance(key.getAlgorithm)
      mac.init(key)
      val hmacbytes = mac.doFinal(data.getBytes())
      DatatypeConverter.printBase64Binary(hmacbytes)
    }

    val encodedUri = URLEncoder.encode(targetUri.toLowerCase(), utf8).toLowerCase
    val expiration = Math.round(new Date(System.currentTimeMillis() + timeout.toMillis).getTime / 1000)
    val hmac = URLEncoder.encode(calculateHmac(s"$encodedUri\n$expiration"), utf8)

    s"SharedAccessSignature sig=$hmac&se=$expiration&skn=$key&sr=$encodedUri"
  }

}
