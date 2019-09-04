package com.demo.utils

/**
 * 操作失败的响应
 * @param msg
 * @return
 */
fun <T> fail(msg: String): Response<T> {
  return response(-1, msg)
}

/**
 * 操作失败的响应
 */
fun <T> fail(): Response<T> {
  return response(-1)
}

/**
 * 操作失败的响应
 */
fun <T> fail(errorCode: ErrorCode): Response<T> {
  return Response(errorCode)
}

/**
 * 操作失败的响应
 */
fun <T> failOnException(e: Exception): Response<T> {
  return response<T>(-1, e.message)
}


/**
 * Created by 吴昊 on 2017/10/25.
 */
fun <T> response(code: Int, msg: String? = null): Response<T> {
  return Response(code, msg)
}

/**
 * 操作成功的响应
 * @param msg 描述
 */
fun <T> success(msg: String): Response<T> {
  return response(0, msg)
}

/**
 * 操作成功的响应
 */
fun <T> success(): Response<T> {
  return response(0)
}

/**
 * 操作成功的响应
 */
fun <T> successWithCode(code: Int): Response<T> {
  return response(code)
}

/**
 * 操作成功的响应
 */
fun <T> successWithData(data: T?): Response<T> {
  return response<T>(0).apply {
    this.data = data
  }
}
