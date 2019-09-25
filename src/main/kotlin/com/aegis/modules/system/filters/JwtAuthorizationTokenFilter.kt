package com.aegis.modules.system.filters

import com.aegis.modules.system.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.context.SecurityContextHolder
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


/**
 * token 拦截器
 * Created by yeqinhua on 2019/9/25.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Component
class JwtAuthorizationTokenFilter(private val jwtTokenUtil: JwtTokenUtil) : OncePerRequestFilter() {

  @Autowired
  private val redisTemplate: RedisTemplate<String, String>? = null

  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
    val requestHeader = request.getHeader("Authorization")
    val authToken: String?
    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
      authToken = requestHeader.substring(7)
      val check = jwtTokenUtil.validateJWT(authToken)
      if (check.success && check.auth != null) {
        SecurityContextHolder.getContext().authentication = check.auth
      }
    }
    chain.doFilter(request, response)
  }
}