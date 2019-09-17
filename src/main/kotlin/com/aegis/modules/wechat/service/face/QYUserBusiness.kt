package com.aegis.modules.wechat.service.face

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * 企业微信oauth2业务接口
 * Created by yeqinhua on 2019/08/12.
 * @since 0.0.1
 */
@FeignClient(name = "oauth2", url = "https://qyapi.weixin.qq.com/cgi-bin/")
interface QYUserBusiness {

  /**
   * 获取当前用户的userid
   * https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE
   * @param code 小程序授权返回的code
   * @param accessToken
   */
  @GetMapping("/user/getuserinfo")
  fun codeToUserId(@RequestParam("code") code: String, @RequestParam("access_token") accessToken: String): MutableMap<String, Any>

  /**
   * 获取用户信息
   * https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID
   * @param userId 用户id
   * @param accessToken
   */
  @GetMapping("/user/get")
  fun userIdToUser(@RequestParam("userid") userId: String, @RequestParam("access_token") accessToken: String): MutableMap<String, Any>
}
