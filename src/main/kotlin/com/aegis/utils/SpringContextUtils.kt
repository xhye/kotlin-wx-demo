package com.aegis.utils

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * SpringContext工具类
 * @Author 黄荣装
 * @Date 9:39 2018/9/17
 * @since 0.0.1
 */
@Component
class SpringContextUtils : ApplicationContextAware {

  companion object {
    lateinit var applicationContext: ApplicationContext

    fun containsBean(name: String): Boolean {
      return applicationContext.containsBean(name)
    }

    fun <T> getBean(name: String, requiredType: Class<T>): T {
      return applicationContext.getBean(name, requiredType)
    }

    fun getBean(name: String?): Any {
      return applicationContext.getBean(name!!)
    }

    fun <T> getBeanByType(requiredType: Class<T>): T {
      return applicationContext.getBean(requiredType)
    }

    fun getType(name: String): Class<out Any>? {
      return applicationContext.getType(name)
    }

    fun isSingleton(name: String): Boolean {
      return applicationContext.isSingleton(name)
    }
  }

  @Throws(BeansException::class)
  override fun setApplicationContext(applicationContext: ApplicationContext) {
    SpringContextUtils.applicationContext = applicationContext
  }

}
