package com.aegis.modules.system.model.entity

import io.jsonwebtoken.Claims
import org.springframework.security.core.Authentication

data class JWTCheck(
  var success: Boolean = false,
  var ErrCode: Int = 400000,
  var claims: Claims? = null,
  var auth: Authentication? = null
)