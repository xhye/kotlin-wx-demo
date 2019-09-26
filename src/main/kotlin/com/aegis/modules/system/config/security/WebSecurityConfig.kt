package com.aegis.modules.system.config.security


import com.aegis.modules.system.config.jwt.JwtAccessDeniedHandler
import com.aegis.modules.system.config.jwt.JwtAuthenticationEntryPoint
import com.aegis.modules.system.config.filters.JwtAuthorizationTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * spring security 配置
 * Created by yeqinhua on 2019/9/17.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity// 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
class WebSecurityConfig() : WebSecurityConfigurerAdapter() {

  @Autowired
  private val authenticationTokenFilter: JwtAuthorizationTokenFilter? = null
  @Autowired
  private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint? = null
  @Autowired
  private val jwtAccessDeniedHandler: JwtAccessDeniedHandler? = null

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
      .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)  // 未授权
      .accessDeniedHandler(jwtAccessDeniedHandler) // 无权
      .and()
      .authorizeRequests()
      .antMatchers("/qyuser/code2userid", "/system/login").permitAll()
      .anyRequest().authenticated()    // 剩下所有的验证都需要验证
      .and()
      // 禁用 Spring Security 自带的跨域处理
      .csrf().disable()
      // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // 禁用缓存
    http.headers().cacheControl()
    http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
  }
}