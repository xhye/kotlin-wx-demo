package com.aegis.modules.system.controller

import com.aegis.bean.Response
import com.aegis.kotlin.successWithData
import com.aegis.modules.demo.service.DemoService
import com.aegis.modules.system.entity.WorkUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 登录
 * Created by yeqinhua on 2019/9/17.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@RestController
@RequestMapping("system")
class LoginActon(private val demoService: DemoService) {
  @PostMapping("login")
  fun post(user: WorkUser) {
    println("登录信息 $user")
  }

  @RequestMapping("error")
  fun error(req:HttpServletRequest, res: HttpServletResponse): Response<Any> {
    res.status = 401
    return successWithData("请先登录")
  }

  @GetMapping("user")
  fun test(principal: Principal): Response<Any> {  // Authentication authentication
    println("principal $principal")
    return successWithData(principal)
  }
}