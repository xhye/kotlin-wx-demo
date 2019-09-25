package com.aegis.modules.system.utils

import com.aegis.modules.system.model.entity.JWTCheck
import io.jsonwebtoken.*
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * jwt 工具类
 */
@Component
class JwtTokenUtil {
  @Value("\${jwt.secret}")
  private val secret: String? = null

  @Value("\${jwt.expiration}")
  private val expiration: Long? = null

  @Value("\${jwt.token}")
  private val headerPrefix: String? = null


  companion object {
    private val AUTHORITIES_KEY = "auth"
  }

  /**
   * 签发JWT
   *
   * @param authentication  权限
   * @param rememberMe  是否保存用户
   * @return String
   */
  fun createJWT(authentication: Authentication, rememberMe: Boolean): String {
    val temp = authentication.authorities.joinToString { it.authority }
    val signatureAlgorithm = SignatureAlgorithm.HS256
    val nowMillis = System.currentTimeMillis()
    val now = Date(nowMillis)
    val secretKey = generalKey()
    val builder = Jwts.builder()
      .setSubject(authentication.name)   // 主题
      .setIssuer("359396547@qq.com")     // 签发者
      .setIssuedAt(now)      // 签发时间
      .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
      .claim(AUTHORITIES_KEY, temp) //记录用户
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
   * 验证JWT
   * @param token
   * @return
   */
  fun validateJWT(token: String): JWTCheck {
    val check = JWTCheck()
    val claims: Claims?
    try {
      claims = parseJWT(token)
      check.success = true
      check.claims = claims
      val rights = claims[AUTHORITIES_KEY].toString().split(",")
      val authorities = ArrayList<GrantedAuthority>()
      rights.forEach {
        authorities.add(SimpleGrantedAuthority(it))
      }
      val principal = User(claims.subject, "", authorities)
      check.auth = UsernamePasswordAuthenticationToken(principal, token, authorities)
    } catch (e: ExpiredJwtException) {
      check.ErrCode = 400001
      check.success = false
    } catch (e: SignatureException) {
      check.ErrCode = 400002
      check.success = false
    } catch (e: Exception) {
      check.success = false
    }
    return check
  }

  private fun generalKey(): SecretKey {
    val base64 = Base64()
    val encodedKey = base64.decode(secret)
    return SecretKeySpec(encodedKey, 0, encodedKey.size, "AES")
  }

  /**
   * 解析JWT字符串
   *
   * @param jwt
   * @return
   * @throws Exception
   */
  @Throws(Exception::class)
  private fun parseJWT(jwt: String): Claims {
    val secretKey = generalKey()
    return Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(jwt)
      .body
  }
}