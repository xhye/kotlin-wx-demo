package com.aegis.modules.qywork.service.impl

import com.aegis.modules.qywork.model.vo.JSSDKConfig
import com.aegis.modules.qywork.service.QYConfigService
import com.aegis.modules.qywork.service.face.QYUserBusiness
import com.aegis.modules.qywork.utils.QYWorkUtils
import org.springframework.stereotype.Service
import java.util.*

/**
 * 微信jssdk相关实现类
 * Created by yeqinhua on 2019/1/10.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Service
class QYConfigServiceImpl(private val wechatUtils: QYWorkUtils, private val oauth2Business: QYUserBusiness) : QYConfigService {

  override fun getConfig(url: String, nonceStr: String, timestamp: String, isAgent: Boolean): JSSDKConfig {
    val config = JSSDKConfig().apply {
      this.appId = "ww4783b5de3fda6acd"
      this.nonceStr = UUID.randomUUID().toString()
      this.timestamp = QYWorkUtils.getCurrentTimestamp().toString()
    }
    val map = TreeMap<String, String>()
    // 两种 ticket
    val ticket = if (isAgent) wechatUtils.getAgentJsApiTicket() else wechatUtils.getJsApiTicket()
    map.putAll(mapOf(
        "jsapi_ticket" to ticket,
        "noncestr" to if (isAgent) nonceStr else config.nonceStr!!,
        "timestamp" to if (isAgent) timestamp else config.timestamp!!,
        "url" to url
    ))
    config.signature = QYWorkUtils.signBySha1(map)
    config.base = map
    return config
  }
}