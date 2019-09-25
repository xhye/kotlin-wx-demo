package com.aegis.modules.system.controller

import com.aegis.bean.Response
import com.aegis.kotlin.successWithCode
import com.aegis.kotlin.successWithData
import com.aegis.modules.system.model.entity.WorkUser
import com.aegis.modules.system.service.WorkUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.annotation.security.RolesAllowed

/**
 * 登录
 * Created by yeqinhua on 2019/9/17.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@RestController
@RequestMapping("system")
class LoginActon(private val workUserService: WorkUserService) {

  @PostMapping("login")
  fun login(user: WorkUser): Response<Any> {
    if (user.userId == null) {
      return successWithCode(201)
    }
    return successWithData(workUserService.loginByUserIdAndGenerateToken(user.userId!!))
  }

  @PostMapping("logout")
  fun logout(): Response<Any> {
    return successWithData(true)
  }
  @GetMapping("user")
  @RolesAllowed("GUEST")
  fun test(principal: Principal): Response<Any> {  // Authentication authentication
    println("principal $principal")
    return successWithData(principal)
  }
}