package com.aegis.modules.system.config

import com.aegis.kotlin.successWithData
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import wuhao.tools.utils.JsonUtils
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * 登录失败处理
 * Created by yeqinhua on 2019/9/17.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Component
class LoginSuccessHandler(private val objectMapper: ObjectMapper) : SimpleUrlAuthenticationSuccessHandler() {
  private val log = LoggerFactory.getLogger(LoginSuccessHandler::class.java)
  override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
    log.info("登录成功")
    response!!.contentType = "application/json;charset=UTF-8"
    response.writer.write(JsonUtils.toJson(successWithData(authentication))!!)
  }
}
