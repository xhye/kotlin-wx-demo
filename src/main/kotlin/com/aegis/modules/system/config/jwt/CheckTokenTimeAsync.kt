package com.aegis.modules.system.config.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class CheckTokenTimeAsync {
  @Value("\${jwt.expiration}")
  private val expiration: Long? = null

  @Async
  fun checkRedisKeyTime(redisTemplate: RedisTemplate<String, String>, token: String) {
    val rest = redisTemplate.getExpire(token, TimeUnit.SECONDS)
    if (300 >= rest) { // 低于五分钟 刷新token时间
      redisTemplate.expire(token, expiration!! / 100, TimeUnit.SECONDS)
    }
  }
}