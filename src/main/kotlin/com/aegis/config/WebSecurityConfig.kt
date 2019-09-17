package com.aegis.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.web.context.SecurityContextPersistenceFilter


@Configuration
@EnableWebSecurity
open class WebSecurityConfig(private val myUserService: UserDetailsService, private val loginSuccessHandler: LoginSuccessHandler, private val loginFailureHandler: LoginFailureHandler) : WebSecurityConfigurerAdapter() {

  @Autowired
  private val securityAccessTokenFilter: SecurityAccessTokenFilter? = null

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http.addFilterAfter(securityAccessTokenFilter, SecurityContextPersistenceFilter::class.java)
    http.formLogin()                                // 定义当需要用户登录时候，转到的登录页面。
      .loginPage("/login")                        // 设置登录页面
      .loginProcessingUrl("/user/login")          // 自定义的登录接口
      .successHandler(loginSuccessHandler)
      .failureHandler(loginFailureHandler)
//      .defaultSuccessUrl("/home").permitAll()     // 登录成功之后，默认跳转的页面
      .and().authorizeRequests()                  // 定义哪些URL需要被保护、哪些不需要被保护
      .antMatchers("/user/login","/demo/*").permitAll()       // 设置所有人都可以访问登录页面
      .anyRequest().authenticated()               // 任何请求,登录后可以访问
      .and().csrf().disable()                   // 关闭csrf防护
  }

  @Throws(Exception::class)
  override fun configure(web: WebSecurity) {
    web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js")
  }

  @Bean
  fun passwordEncoder(): BCryptPasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Throws(Exception::class)
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(myUserService)
      .passwordEncoder(passwordEncoder())
  }
}