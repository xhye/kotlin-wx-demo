package com.demo.modules.wechat.model.vo


/**
 * jsapi_ticket实体类
 * Created by chenwenjian on 2019/1/10.
 *
 * @author chenwenjian
 * @since 0.0.1
 */
data class JSAPITicketVO(
    var ticket: String? = null,
    var expiresIn: Int? = null
) : BaseVO()
