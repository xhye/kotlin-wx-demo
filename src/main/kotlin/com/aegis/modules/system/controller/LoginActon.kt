package com.aegis.modules.system.controller

import com.aegis.bean.Response
import com.aegis.kotlin.response
import com.aegis.kotlin.successWithData
import com.aegis.modules.qywork.service.QYUserService
import com.aegis.modules.system.model.entity.WorkUser
import com.aegis.modules.system.service.WorkUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.*
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
class LoginActon(private val workUserService: WorkUserService, private val qyUserServicee: QYUserService) {

  @Autowired
  private val redisTemplate: RedisTemplate<String, String>? = null

  @PostMapping("login")
  fun login(@RequestBody user: WorkUser): Response<Any> {
    val resultMap = qyUserServicee.codeToUserId(user.code!!)
    return if (resultMap["isQYUser"] as Boolean) {
      successWithData(workUserService.loginByUserIdAndGenerateToken(resultMap["userId"].toString()))
    } else {
      response(200, "你不是当前企业用户！")
    }
  }

  @PostMapping("logout")
  fun logout(principal: Principal): Response<Any> {
    println("principal.name ${principal.name}")
    redisTemplate!!.delete(principal.name)
    return successWithData(true)
  }

  @GetMapping("user")
  @RolesAllowed("GUEST")
  fun test(principal: Principal): Response<Any> {  // Authentication authentication
    return successWithData(principal)
  }
}