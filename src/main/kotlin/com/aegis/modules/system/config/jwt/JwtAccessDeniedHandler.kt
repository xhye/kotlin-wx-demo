package com.aegis.modules.system.config.jwt

import com.aegis.kotlin.failOnException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import wuhao.tools.utils.JsonUtils
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * jwt无权角色权限处理
 */
@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
  private val log = LoggerFactory.getLogger(AccessDeniedHandler::class.java)


  @Throws(IOException::class)
  override fun handle(req: HttpServletRequest, rep: HttpServletResponse, accessDeniedException: AccessDeniedException) {
    log.error("您暂无权限访问此链接:", accessDeniedException.message!!)
    rep.status = HttpStatus.FORBIDDEN.value()
    rep.contentType = "application/json;charset=UTF-8"
    rep.writer.write(JsonUtils.toJson(failOnException<Any>(Exception(accessDeniedException.message)))!!)

  }
}