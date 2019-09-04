package com.demo.modules.wechat.controller

import com.demo.modules.wechat.service.QYConfigService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 微信jssdk 配置文件注册接口
 * Created by 叶琴华 on 2019/1/10.
 * @author 叶琴华
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
  fun config(url: String, isAgent: Boolean): Any {
    return wechatJsSdkService.getConfig(url, isAgent)
  }

}
