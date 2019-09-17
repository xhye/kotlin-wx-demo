package com.aegis.config

import org.springframework.stereotype.Component
import javax.servlet.*

@Component
class SecurityAccessTokenFilter : Filter {
  override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
    println(req)
    chain.doFilter(req, res)
  }

  override fun destroy() {
  }

  override fun init(filterConfig: FilterConfig?) {
  }

}