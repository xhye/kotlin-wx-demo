package com.aegis.modules.system.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

/**
 * 企业微信用户
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
data class WorkUser (
    /**  id */
    var id: Long? = null,
    /**  名称 */
    var username: String? = null,
    /**  密码 */
    var password: String? = null
)
