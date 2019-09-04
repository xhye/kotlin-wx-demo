package com.demo.utils

import java.util.*

/**
 * 线程本地变量工具类
 * Created by yeqinhua on 2019/08/12.
 * @since 0.0.1
 */
object ThreadLocalUtil {

  /**
   * 本地线程变量
   */
  private val THREAD_LOCAL = ThreadLocal<MutableMap<String, Any>?>()

  /**
   * 存线程本地变量
   * @param key
   * @param value
   */
  fun set(key: String, value: Any) {
    var map: MutableMap<String, Any>? = THREAD_LOCAL.get()
    if (map == null) {
      map = HashMap()
    }
    map[key] = value
    THREAD_LOCAL.set(map)
  }

  /**
   * 取线程本地变量
   * @param key
   * @return T 返回泛型
   * @return T
   */
  fun <T> get(key: String): T? {
    val map = THREAD_LOCAL.get()
    return if (map != null) {
      map[key] as T
    } else {
      null
    }
  }

  /**
   * 清理
   */
  fun clear() {
    THREAD_LOCAL.remove()
  }
}