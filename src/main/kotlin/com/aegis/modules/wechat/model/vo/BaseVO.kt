package com.aegis.modules.wechat.model.vo

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming


/**
 * 微信接口返回共同属性
 * Created by yeqinhua on 2019/1/10.
 * @author yeqinhua
 * @since 0.0.1
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
open class BaseVO {

  /**  返回码 */
  var code: Int? = null
  /**  错误码 */
  var errcode: Int? = null
  /**  错误信息 */
  var errmsg: String? = null

}

