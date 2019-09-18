package com.aegis.modules.qywork.controller

import com.aegis.modules.qywork.service.QYConfigService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 微信jssdk 配置文件注册接口
 * Created by yeqinhua on 2019/1/10.
 * @author yeqinhua
 * @since 0.0.1
 */
@RestController
@RequestMapping("wechat_jssdk")
class QYJSSDKAction(private val wechatJsSdkService: QYConfigService) {

  /**
   * 获取使用wx jssdk config
   * @param url 域名
   * @param  isAgent 是否 agentConfig
   */
  @GetMapping("config")
  fun config(url: String, nonceStr: String, timestamp: String, isAgent: Boolean): Any {
    return wechatJsSdkService.getConfig(url, nonceStr, timestamp, isAgent)
  }

}
