package com.aegis.config

import com.aegis.kotlin.failOnException
import com.aegis.kotlin.successWithData
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import javax.servlet.ServletException
import java.io.IOException
import java.lang.Exception
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest


@Component
class LoginFailureHandler(private val objectMapper: ObjectMapper) : SimpleUrlAuthenticationFailureHandler() {

  private val log = LoggerFactory.getLogger(LoginFailureHandler::class.java)

  @Throws(IOException::class, ServletException::class)
  override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?, exception: org.springframework.security.core.AuthenticationException?) {
    log.info("登录失败")
    exception!!.printStackTrace()
    response!!.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
    response.contentType = "application/json;charset=UTF-8"
    response.writer.write(objectMapper.writeValueAsString(failOnException<Any>(Exception(exception!!.message))))
  }

}