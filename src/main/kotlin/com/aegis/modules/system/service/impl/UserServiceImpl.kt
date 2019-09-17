package com.aegis.modules.system.service.impl

import com.aegis.modules.system.service.WorkUserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.ArrayList
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


/**
 * 企业微信用户业务接口实现
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Service("myUserService")
class UserServiceImpl(private val workUserService: WorkUserService) : UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails? {
//    AuthorityUtils.commaSeparatedStringToAuthorityList("user")
    val authorities = ArrayList<GrantedAuthority>()
    authorities.add(SimpleGrantedAuthority("ADMIN"))
    val workUser = workUserService.loadWorkUserByName(username)
    return if (workUser != null) { // 加密
      User(workUser.username, BCryptPasswordEncoder().encode(workUser.password), authorities);
    } else {
      null
    }
  }
}