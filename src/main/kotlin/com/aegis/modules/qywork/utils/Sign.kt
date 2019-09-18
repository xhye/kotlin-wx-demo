package com.aegis.modules.qywork.utils

import org.apache.commons.codec.digest.DigestUtils
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.HashMap

/**
 * 签名
 */
class Sign {

  private fun createNonceStr(): String {
    return UUID.randomUUID().toString()
  }

  private fun createTimestamp(): String {
    return (System.currentTimeMillis() / 1000).toString()
  }


  fun sign(jsapi_ticket: String, url: String): Map<String, String> {
    val ret = HashMap<String, String>()
    val nonce_str = createNonceStr()
    val timestamp = createTimestamp()
    val string1: String
    var signature = ""
    //注意这里参数名必须全部小写，且必须有序
    string1 = "jsapi_ticket=" + jsapi_ticket +
      "&noncestr=" + nonce_str +
      "&timestamp=" + timestamp +
      "&url=" + url
    println(string1)

    try {
      val crypt = MessageDigest.getInstance("SHA-1")
      crypt.reset()
      crypt.update(string1.toByteArray(charset("UTF-8")))
      signature = byteToHex(crypt.digest())
    } catch (e: NoSuchAlgorithmException) {
      e.printStackTrace()
    } catch (e: UnsupportedEncodingException) {
      e.printStackTrace()
    }

    ret["url"] = url
    ret["jsapi_ticket"] = jsapi_ticket
    ret["nonceStr"] = nonce_str
    ret["timestamp"] = timestamp
    ret["signature"] = signature

    return ret
  }

  /**
   * 按字典排序map并使用sha1加密返回签名
   * @param map 需要加密的map
   * @return
   */
  fun sign(map: HashMap<String, Any>): String? {
    try {
      val treeMap = TreeMap<String, Any>()
      for (key in map.keys) {
        treeMap.put(map[key].toString(), map[key]!!)
      }
      val sb = StringBuilder()
      for (key in treeMap.keys) {
        sb.append(key)
      }
      return DigestUtils.shaHex(sb.toString().toByteArray(charset("UTF-8")))
    } catch (e: UnsupportedEncodingException) {
      e.printStackTrace()
    }
    return null
  }

  private fun byteToHex(hash: ByteArray): String {
    val formatter = Formatter()
    for (b in hash) {
      formatter.format("%02x", b)
    }
    val result = formatter.toString()
    formatter.close()
    return result
  }

}
