package com.aegis.modules.system.config.redis

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * redis 注册
 * Created by yeqinhua on 2019/9/17.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Configuration
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {

  @Bean
  fun <T> redisTemplateKeyString(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, T> {
    val redisTemplate = RedisTemplate<String, T>()
    redisTemplate.setConnectionFactory(redisConnectionFactory)
    val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)
    val objectMapper = ObjectMapper()
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper)
    redisTemplate.keySerializer = StringRedisSerializer()
    redisTemplate.valueSerializer = jackson2JsonRedisSerializer
    redisTemplate.afterPropertiesSet()
    return redisTemplate
  }
}