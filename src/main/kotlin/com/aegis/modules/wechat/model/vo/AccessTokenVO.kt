package com.aegis.modules.wechat.model.vo


/**
 *
 * Created by 程格聪 on 2019/1/10.
 * 微信获取accessToken接口返回实体类
 * 请求成功只返回 access_token和expires_in
 * 失败只返回 errcode和errmsg
 * @author 程格聪
 * @since 0.0.1
 */

data class AccessTokenVO(
    /**  请求成功返回的accessToken */
    var accessToken: String? = null,
    /**  返回的accessToken有效期 单位：秒 */
    var expiresIn: Int? = null
) : BaseVO()
