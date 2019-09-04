package com.demo.modules.wechat.service

/**
 * 企业微信用户业务接口
 * Created by yeqinhua on 2019/8/20.
 * @author yeqinhua
 * @since 0.0.1
 */
interface QYUserService {

  /**
   * 企业微信授权获得的code 换取当前企业微信用户信息
   * @param code 授权获得的code
   * @return
   */
  fun codeToUserId(code: String): MutableMap<String, Any>

  /**
   * 读取成员
   * @param userId 用户id
   * @return
   */
  fun userId2User(userId: String): MutableMap<String, Any>
}