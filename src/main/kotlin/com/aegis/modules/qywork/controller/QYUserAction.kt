package com.aegis.modules.qywork.controller

import com.aegis.bean.Response
import com.aegis.kotlin.successWithData
import com.aegis.modules.qywork.service.QYUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RequestMapping("qyuser")
@RestController
class QYUserAction(private val qyUserServicee: QYUserService) {
  /**
   * 授权code换取企业微信用户userid
   */
  @GetMapping("/code2userid")
  fun code2User(code: String): Response<Any> {
    return successWithData(qyUserServicee.codeToUserId(code))
  }
  /**
   * userId换取用户信息
   */
  @GetMapping("/get/{userId}")
  fun getUser(@PathVariable("userId") userId: String): Response<Any> {
    return successWithData(qyUserServicee.userId2User(userId))
  }

}