package com.aegis.modules.qywork.service

import com.aegis.modules.qywork.model.vo.JSSDKConfig

/**
 *
 * 企业微信配置
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