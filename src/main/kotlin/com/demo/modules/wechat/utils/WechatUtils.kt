package com.demo.modules.wechat.utils


import com.demo.modules.wechat.exception.QYException
import com.demo.modules.wechat.model.entiry.AccessToken
import com.demo.modules.wechat.service.face.QYBusiness
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.stereotype.Component
import java.util.*


/**
 * 微信工具类
 * Created by chenwenjian on 2019/1/10.
 *
 * @author chenwenjian
 * @since 0.0.1
 */
@Component
class WechatUtils(private val qywxBusiness: QYBusiness) {
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

  @Volatile
  private var accessToken: AccessToken? = null

  /**
   * 获取AccessToken
   */
  @Synchronized
  fun getAccessToken(): String {
    val currentTime = System.currentTimeMillis()
    if (accessToken == null || accessToken!!.expires < currentTime) {
      val accessTokenVO = qywxBusiness.gettoken("ww4783b5de3fda6acd", "EQKTdb-LGV1YFazVs7cgH_U5ReXPX3Hwgeasjwv0R4U")
      if (accessTokenVO.accessToken != null) {
        val expired = accessTokenVO.expiresIn as Int
        this.accessToken = AccessToken(accessTokenVO.accessToken as String, currentTime + ((expired - 60) * 1000))
      } else {
        throw QYException(accessTokenVO)
      }
    }
    return accessToken!!.token
  }

  /**
   * 获取jsapi_ticket
   */
  @Synchronized
  fun getJsApiTicket(): String {
    val ticketVO = qywxBusiness.getJSSDKTicket(getAccessToken())
    if (ticketVO.errcode.toString() != "0") {
      throw QYException(ticketVO)
    }
    return ticketVO.ticket!!

  }
  /**
   * 获取 agent jsapi_ticket
   */
  @Synchronized
  fun getAgentJsApiTicket(): String {
    val ticketVO = qywxBusiness.getAgentJSSDKTicket(getAccessToken())
    if (ticketVO.errcode.toString() != "0") {
      throw QYException(ticketVO)
    }
    return ticketVO.ticket!!

  }
}
