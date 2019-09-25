package com.aegis.modules.system.model.entity


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * 企业微信用户
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */


data class WorkUser(
  /**
   * 企业微信用户id
   */
  var id: Int? = null,
  /**
   * 企业微信用户id
   */
  var userId: String? = null,
  /**
   * token
   */
  var token: String? = null,
  /**
   * 用户名
   */
  private var username: String? = null,
  /**
   * 密码
   */
  private var password: String? = null,
  /**
   * 是否可用
   */
  /**
   * 用户所拥有的权限
   */
  var authorities: List<GrantedAuthority> = emptyList(),

  /**
   * 用户的账号是否过期,过期的账号无法通过授权验证. true 账号未过期
   */
  var accountNonExpired: Boolean = true,

  /**
   * 用户的账户是否被锁定,被锁定的账户无法通过授权验证. true 账号未锁定
   */
  var accountNonLocked: Boolean = true,

  /**
   * 用户的凭据(pasword) 是否过期,过期的凭据不能通过验证. true 没有过期,false 已过期
   */
  var credentialsNonExpired: Boolean = true,

  var enabled: Boolean = true) : UserDetails {


  override fun getAuthorities(): Collection<GrantedAuthority>? {
    return authorities
  }

  override fun getPassword(): String? {
    return password
  }

  override fun getUsername(): String? {
    return username
  }

  override fun isAccountNonExpired(): Boolean {
    return accountNonExpired
  }

  override fun isAccountNonLocked(): Boolean {
    return accountNonLocked
  }

  override fun isCredentialsNonExpired(): Boolean {
    return credentialsNonExpired
  }

  override fun isEnabled(): Boolean {
    return enabled!!
  }
}
