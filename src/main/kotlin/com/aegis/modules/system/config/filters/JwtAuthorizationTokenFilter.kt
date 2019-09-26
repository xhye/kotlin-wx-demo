package com.aegis.modules.system.config.filters

import com.aegis.modules.system.config.jwt.CheckTokenTimeAsync
import com.aegis.modules.system.model.entity.WorkUser
import com.aegis.modules.system.utils.JwtTokenUtil
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import wuhao.tools.utils.JsonUtils
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * token 拦截器
 * Created by yeqinhua on 2019/9/25.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Component
@EnableAsync // 开启异步
class JwtAuthorizationTokenFilter(private val jwtTokenUtil: JwtTokenUtil) : OncePerRequestFilter() {

  @Value("\${jwt.headerKey}")
  private val headerKey: String? = null
  @Value("\${jwt.headerPrefix}")
  private val headerPrefix: String? = null
  @Value("\${jwt.expiration}")
  private val expiration: Long? = null
  @Autowired
  private val redisTemplate: RedisTemplate<String, String>? = null
  @Autowired
  private val checkTokenTimeAsync: CheckTokenTimeAsync? = null

  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
    val requestHeader = request.getHeader(headerKey)
    val authToken: String?
    if (requestHeader != null && requestHeader.startsWith("$headerPrefix ")) {
      authToken = requestHeader.substring(7)
      val userJson = redisTemplate!!.opsForValue().get(authToken) // 以缓存为准
      if (userJson != null) {
        val check = jwtTokenUtil.validateJWT(authToken)
        val workUser = JsonUtils.parse(userJson, WorkUser::class.java)
        if (check.success) {
          SecurityContextHolder.getContext().authentication = check.auth
          //检查token生命 是否需要刷新token
          checkTokenTimeAsync!!.checkRedisKeyTime(redisTemplate,authToken)
        } else if (!check.success && check.errCode == 400001) { // 过期重新签发token
          val newToken = jwtTokenUtil.createJWT(workUser,true)
          workUser!!.token = newToken
          SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(workUser, workUser.password, workUser.getAuthorities())
          redisTemplate.opsForValue().set(newToken, JsonUtils.toJson(workUser)!!, expiration!!/1000, TimeUnit.SECONDS)
          response.setHeader(headerKey, newToken)
        }
      }
    }
    chain.doFilter(request, response)
  }
}