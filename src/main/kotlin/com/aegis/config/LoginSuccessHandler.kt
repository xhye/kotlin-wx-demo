package com.aegis.config

import com.aegis.kotlin.successWithData
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest


@Component
class LoginSuccessHandler(private val objectMapper: ObjectMapper) : SimpleUrlAuthenticationSuccessHandler() {
  private val log = LoggerFactory.getLogger(LoginSuccessHandler::class.java)
  override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
    log.info("登录成功")
    response!!.contentType = "application/json;charset=UTF-8"
    response.writer.write(objectMapper.writeValueAsString(successWithData(authentication)))
  }
}
