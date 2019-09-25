package com.aegis.modules.system.config

import com.aegis.bean.Response
import com.aegis.kotlin.failOnException
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 全局异常拦截
 */
@ControllerAdvice
class GlobalControllerAdvice {

  private val log = LoggerFactory.getLogger(GlobalControllerAdvice::class.java)

  @ResponseBody
  @ExceptionHandler(value = [Exception::class])
  fun javaExceptionHandler(ex: Exception): Response<Any> {
    log.error(ex.message)
    //异常日志入库
    return failOnException(ex)
  }
}