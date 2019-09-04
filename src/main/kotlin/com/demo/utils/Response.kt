package com.demo.utils

/**
 * http接口响应数据对象
 * @author 吴昊
 * @since 0.1
 */
open class Response<T> {

  open var data: T? = null
  var code: Int = 0
  var msg: String? = null

  constructor()

  constructor(code: Int) {
    this.code = code
  }

  constructor(data: T) {
    this.data = data
  }

  constructor(errorCode: ErrorCode) {
    this.code = errorCode.code
    this.msg = errorCode.zhName
  }

  constructor(code: Int, msg: String?) : this(code) {
    this.msg = msg
  }

}