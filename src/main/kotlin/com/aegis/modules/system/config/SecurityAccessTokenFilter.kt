package com.aegis.modules.system.config

import org.springframework.stereotype.Component
import javax.servlet.*
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder

/**
 * spring security 过滤器
 * Created by yeqinhua on 2019/9/17.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Component
class SecurityAccessTokenFilter : Filter {

  @Autowired
  private val redisTemplate: RedisTemplate<String, String>? = null

  override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
//    val user = SecurityContextHolder.getContext().authentication.principal
//    println("user $user")
    chain.doFilter(req, res)
  }

  override fun destroy() {
  }

  override fun init(filterConfig: FilterConfig?) {
  }

}