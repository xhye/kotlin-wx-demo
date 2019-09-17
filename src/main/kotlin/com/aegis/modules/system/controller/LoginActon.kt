package com.aegis.modules.system.controller

import com.aegis.modules.demo.service.DemoService
import com.aegis.modules.system.entity.WorkUser
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 登录
 * Created by {creator} on {create date}.
 *
 * @author {creator}
 * @since 0.0.1
 */
@RestController
@RequestMapping("user")
class LoginActon(private val demoService: DemoService) {
  @PostMapping("login")
  fun post(user: WorkUser) {
    println(user)
  }
}