package com.aegis.modules.wechat.model.vo


/**
 * jsapi_ticket实体类
 * Created by yeqinhua on 2019/1/10.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
data class JSAPITicketVO(
    var ticket: String? = null,
    var expiresIn: Int? = null
) : BaseVO()
