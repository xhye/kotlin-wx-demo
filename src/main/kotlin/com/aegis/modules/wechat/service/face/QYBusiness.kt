package com.aegis.modules.wechat.service.face

import com.aegis.modules.wechat.model.vo.AccessTokenVO
import com.aegis.modules.wechat.model.vo.JSAPITicketVO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * 企业微信业务接口
 * Created by yeqinhua on 2019/08/12.
 * @since 0.0.1
 */
@FeignClient(name = "qywx", url = "https://qyapi.weixin.qq.com/cgi-bin/")
interface QYBusiness {

  /**
   * 获取 access_token
   * @param corpid 企业id
   * @param corpsecret 应用秘钥
   */
  @GetMapping("gettoken")
  fun gettoken(@RequestParam("corpid") corpid: String, @RequestParam("corpsecret") corpsecret: String): AccessTokenVO


  /**
   *  https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN
   * 获取wx JSSDK config ticket
   * @param accessToken
   * @return
   */
  @GetMapping("get_jsapi_ticket")
  fun getJSSDKTicket(@RequestParam("access_token") accessToken: String): JSAPITicketVO

  /**
   *  https://qyapi.weixin.qq.com/cgi-bin/ticket/get?access_token=ACCESS_TOKEN&type=agent_config
   * 获取wx  agent JSSDK config ticket
   * @param accessToken
   * @return
   */
  @GetMapping("ticket/get?type=agent_config")
  fun getAgentJSSDKTicket(@RequestParam("access_token") accessToken: String): JSAPITicketVO
}