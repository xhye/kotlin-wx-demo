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
  fun authLogin(code: String): Response<Any> {
    return successWithData(qyUserServicee.codeToUserId(code))
  }
  /**
   * userId换取用户信息
   */
  @GetMapping("/get/{userId}")
  fun getUser(@PathVariable("userId") userId: String): Response<Any> {
    return successWithData(qyUserServicee.userId2User(userId))
  }
  /**
   * check
   */
  @GetMapping("/check")
  fun check(request: HttpServletRequest, response: HttpServletResponse): Response<Boolean> {
//    var cookies =  request.cookies
//    if (cookies != null) {
//      for( item in cookies) {
//        println("before----${item.name}:${item.value}")
//      }
//    } else {
//      println("before:[]")
//    }
//    var result = false
//    if(cookies != null){
//      for( item in cookies) {
//        if(item.name.trim() == IS_AUTH) {
//          result = true
//          break
//        }
//      }
//    } else {
//      val cookie = Cookie("IS_AUTH", UUID.randomUUID().toString().replace("-", ""))
//      cookie.isHttpOnly = true
//      cookies = arrayOf(cookie)
//      response.addCookie(cookie)
//    }
//    for( item in cookies) {
//      println("after----${item.name}:${item.value}")
//    }
    return successWithData(false)
  }
}