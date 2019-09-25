package com.aegis.modules.system.service.impl

import com.aegis.modules.system.dao.WorkUserDAO
import com.aegis.modules.system.model.entity.WorkUser
import com.aegis.modules.system.service.WorkUserService
import com.aegis.modules.system.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import wuhao.tools.utils.JsonUtils
import java.util.ArrayList
import java.util.concurrent.TimeUnit

/**
 * 企业微信用户业务接口实现
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Service("workUserService")
class WorkUserServiceImpl(private val workUserDAO: WorkUserDAO, private val jwtTokenUtil: JwtTokenUtil) : WorkUserService, UserDetailsService {

  @Value("\${jwt.expiration}")
  private val expiration: Long? = null


  @Autowired
  private val redisTemplate: RedisTemplate<String, String>? = null

  override fun loginByUserIdAndGenerateToken(userId: String): WorkUser? {
    val authorities = ArrayList<GrantedAuthority>()
    authorities.add(SimpleGrantedAuthority("ADMIN")) // 暂时没有添加用户权限
    authorities.add(SimpleGrantedAuthority("USER")) // 暂时没有添加用户权限
    authorities.add(SimpleGrantedAuthority("GUEST")) // 暂时没有添加用户权限
    val workUser = workUserDAO.loadWorkUserByName(userId)
    if (workUser != null) { // 加密
      workUser.authorities = authorities
      val authentication = UsernamePasswordAuthenticationToken(workUser, null, workUser.authorities)
      workUser.token = jwtTokenUtil.createJWT(authentication,true)
      SecurityContextHolder.getContext().authentication = authentication
      redisTemplate!!.opsForValue().set(workUser.token!!, JsonUtils.toJson(authentication)!!, expiration!!,TimeUnit.SECONDS) // token 用户 存redis
      return workUser
    } else {
      throw UsernameNotFoundException("用户不存在")
    }
  }

  override fun loadUserByUsername(username: String?): UserDetails {
    val authorities = ArrayList<GrantedAuthority>()
    authorities.add(SimpleGrantedAuthority("ADMIN")) // 暂时没有添加用户权限
    val workUser = workUserDAO.loadWorkUserByName(username!!)
    if (workUser != null) {
      workUser.authorities = authorities
      return workUser
    } else {
      throw UsernameNotFoundException("用户名不存在")
    }
  }

  override fun loadWorkUserByUseId(userId: String): WorkUser? {
    return workUserDAO.loadWorkUserByName(userId)
  }
}