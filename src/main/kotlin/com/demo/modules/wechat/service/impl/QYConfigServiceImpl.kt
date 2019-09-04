package com.demo.modules.wechat.service.impl

import com.demo.modules.wechat.model.vo.JSSDKConfig
import com.demo.modules.wechat.service.QYConfigService
import com.demo.modules.wechat.service.face.QYUserBusiness
import com.demo.modules.wechat.utils.WechatUtils
import org.springframework.stereotype.Service
import java.util.*

/**
 * 微信jssdk相关实现类
 * Created by chenwenjian on 2019/1/10.
 *
 * @author chenwenjian
 * @since 0.0.1
 */
@Service
class QYConfigServiceImpl(private val wechatUtils: WechatUtils, private val oauth2Business: QYUserBusiness) : QYConfigService {

  override fun getConfig(url: String, agent: Boolean): JSSDKConfig {
    val config = JSSDKConfig().apply {
      this.appId = "ww4783b5de3fda6acd"
      this.nonceStr = UUID.randomUUID().toString()
      this.timestamp = WechatUtils.getCurrentTimestamp().toString()
    }
    val map = TreeMap<String, String>()
    // 两种 ticket
    val ticket = if (agent) wechatUtils.getAgentJsApiTicket() else wechatUtils.getJsApiTicket()
    map.putAll(mapOf(
        "noncestr" to config.nonceStr!!,
        "jsapi_ticket" to ticket,
        "timestamp" to config.timestamp!!,
        "url" to url
    ))
    config.signature = WechatUtils.signBySha1(map)
    config.base = map
    return config
  }
}