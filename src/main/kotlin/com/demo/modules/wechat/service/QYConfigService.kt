package com.demo.modules.wechat.service

import com.demo.modules.wechat.model.vo.JSSDKConfig

/**
 *
 * Created by chenwenjian on 2019/1/10.
 *
 * @author chenwenjian
 * @since 0.0.1
 */
interface QYConfigService {
  /**
   * 获取JSSDK配置
   * @param url 域名
   * @param agent 是否AgentConfig
   * @return
   */
  fun getConfig(url: String, agent: Boolean): JSSDKConfig

}