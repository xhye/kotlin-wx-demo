package com.aegis.modules.system.model.entity

import org.springframework.security.core.Authentication

/**
 * token校验结果
 * Created by yeqinhua on 2019/9/25.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
data class JWTCheck(
  var success: Boolean = false, // token有效性
  var errCode: Int = 400000, // token校验码
  var auth: Authentication? = null //授权信息 可以通过token换取
)