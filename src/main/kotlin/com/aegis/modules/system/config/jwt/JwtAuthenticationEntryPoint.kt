package com.aegis.modules.system.config.jwt

import com.aegis.kotlin.failOnException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import wuhao.tools.utils.JsonUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 无权限（未登录）
 */
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

  private val log = LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

  override fun commence(res: HttpServletRequest?, req: HttpServletResponse?, auth: AuthenticationException?) {
    log.error("请先登录:", auth!!.message)
    req!!.status = HttpStatus.UNAUTHORIZED.value()
    req.contentType = "application/json;charset=UTF-8"
    req.writer.write(JsonUtils.toJson(failOnException<Any>(Exception(auth.message)))!!)
  }
}