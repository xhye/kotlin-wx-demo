package com.aegis.modules.qywork.utils


import com.aegis.modules.qywork.exception.QYException
import com.aegis.modules.qywork.model.entiry.AccessToken
import com.aegis.modules.qywork.service.face.QYBusiness
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * 微信工具类
 * Created by yeqinhua on 2019/1/10.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Component
class QYWorkUtils(private val qywxBusiness: QYBusiness) {

  @Autowired
  private val redisTemplate: RedisTemplate<String, String>? = null

  companion object {
    /**
     * 获取当前时间戳
     */
    fun getCurrentTimestamp(): Long {
      return System.currentTimeMillis() / 1000
    }

    /**
     * 使用sha1签名map数据
     */
    fun signBySha1(map: TreeMap<String, String>): String {
      val sb = StringBuilder()
      map.entries.joinTo(sb, "&") {
        "${it.key}=${it.value}"
      }
      return DigestUtils.sha1Hex(sb.toString())
    }

  }
//
//  @Volatile
//  private var accessToken: AccessToken? = null

  /**
   * 获取AccessToken
   */
  @Synchronized
  fun getAccessToken(): String {
//    val currentTime = System.currentTimeMillis()
    var accessToken = redisTemplate!!.opsForValue().get("AccessToken")
    if (accessToken == null) {
      val accessTokenVO = qywxBusiness.gettoken("ww4783b5de3fda6acd", "EQKTdb-LGV1YFazVs7cgH_U5ReXPX3Hwgeasjwv0R4U")
      if (accessTokenVO.accessToken != null) {
        val expired = accessTokenVO.expiresIn
        accessToken = accessTokenVO.accessToken as String
        redisTemplate.opsForValue().set("AccessToken", accessToken, expired!!, TimeUnit.SECONDS)
      } else {
        throw QYException(accessTokenVO)
      }
    }
    return accessToken
  }

  /**
   * 获取jsapi_ticket
   */
  @Synchronized
  fun getJsApiTicket(): String {
    val ticket = redisTemplate!!.opsForValue().get("JSSDKTicket")
    if (ticket == null) {
      val ticketVO = qywxBusiness.getJSSDKTicket(getAccessToken())
      if (ticketVO.errcode.toString() != "0") {
        throw QYException(ticketVO)
      }
      redisTemplate.opsForValue().set("JSSDKTicket", ticketVO.ticket!!, ticketVO.expiresIn!!, TimeUnit.SECONDS)
    }
    return ticket!!
  }

  /**
   * 获取 agent jsapi_ticket
   */
  @Synchronized
  fun getAgentJsApiTicket(): String {
    val ticket = redisTemplate!!.opsForValue().get("AgentJSSDKTicket")
    if (ticket == null) {
      val ticketVO = qywxBusiness.getAgentJSSDKTicket(getAccessToken())
      if (ticketVO.errcode.toString() != "0") {
        throw QYException(ticketVO)
      }
      redisTemplate.opsForValue().set("AgentJSSDKTicket", ticketVO.ticket!!, ticketVO.expiresIn!!, TimeUnit.SECONDS)
    }
    return ticket!!
  }
}
