package com.aegis.modules.system.utils

import com.aegis.modules.system.model.entity.JWTCheck
import com.aegis.modules.system.model.entity.WorkUser
import io.jsonwebtoken.*
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * jwt 工具类
 *
 * 欠缺：token 更新 token 过期处理
 */
@Component
class JwtTokenUtil {
  @Value("\${jwt.secret}")
  private val secret: String? = null

  @Value("\${jwt.expiration}")
  private val expiration: Long? = null


  companion object {
    private const val AUTHORITIES_KEY = "auth" // 存放用户信息  可以定义多个
    private const val PASSWORD_KEY = "password"

  }

  /**
   * 签发token
   *
   * @param workUser 用户
   * @param rememberMe  是否保存用户
   * @return String
   */
  fun createJWT(workUser: WorkUser?, rememberMe: Boolean): String {
    val temp = workUser!!.authority.joinToString { it }
    val signatureAlgorithm = SignatureAlgorithm.HS256
    val nowMillis = System.currentTimeMillis()
    val now = Date(nowMillis)
    val secretKey = generalKey()
    val builder = Jwts.builder()
      .setSubject(workUser.username)   // 主题  姓名
//      .setIssuer("359396547@qq.com")     // 签发者
      .setIssuedAt(now)      // 签发时间
      .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
      .claim(AUTHORITIES_KEY, temp) // 记录用户角色
      .claim(PASSWORD_KEY, workUser.password) // 记录用户角色
    if (rememberMe) {
      if (expiration!! >= 0) {
        val expMillis = nowMillis + expiration
        val expDate = Date(expMillis)
        builder.setExpiration(expDate) // 过期时间
      }
    }
    return builder.compact()
  }

  /**
   * 验证token
   *
   * @param token
   * @return
   */
  fun validateJWT(token: String): JWTCheck {
    val check = JWTCheck()
    val claims: Claims?
    try {
      claims = parseJWT(token)
      check.success = true
      val roles = claims[AUTHORITIES_KEY].toString().split(",") // 还原权限
      val authorities = ArrayList<GrantedAuthority>()
      roles.forEach {
        authorities.add(SimpleGrantedAuthority(it))
      }
      val principal = User(claims.subject.toString(), claims[PASSWORD_KEY].toString(), authorities)
      check.auth = UsernamePasswordAuthenticationToken(principal, claims[PASSWORD_KEY].toString(), authorities)
    } catch (e: ExpiredJwtException) {
      check.errCode = 400001  // 过期
      check.success = false
    } catch (e: SignatureException) {
      check.errCode = 400002 // token校验无效
      check.success = false
    } catch (e: Exception) {
      check.success = false  // 其他异常
    }
    return check
  }

  private fun generalKey(): SecretKey {
    val base64 = Base64()
    val encodedKey = base64.decode(secret)
    return SecretKeySpec(encodedKey, 0, encodedKey.size, "AES")
  }

  /**
   * 解析token字符串
   *
   * @param token
   * @return
   * @throws Exception
   */
  @Throws(Exception::class)
  private fun parseJWT(token: String): Claims {
    val secretKey = generalKey()
    return Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token)
      .body
  }
}