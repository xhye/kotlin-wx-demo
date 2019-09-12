package com.demo.modules.wechat.service

import com.demo.modules.wechat.model.vo.JSSDKConfig

/**
 *
 * Created by yeqinhua on 2019/1/10.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
interface QYConfigService {
  /**
   * 获取JSSDK配置
   * @param url 域名
   * @param nonceStr 上一次
   * @param timestamp 域名
   * @param isAgent 是否AgentConfig
   * @return
   */
  fun getConfig(url: String, nonceStr: String, timestamp: String, isAgent: Boolean): JSSDKConfig

}