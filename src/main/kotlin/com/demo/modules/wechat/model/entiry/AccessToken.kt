package com.demo.modules.wechat.model.entiry

/**
 * access_token
 * Created by 陈文健 on 2018/12/19.
 *
 * @author 陈文健
 * @since 0.0.1
 */
data class AccessToken(
    /**  token字符串 */
    var token: String,
    /**  过期时间 */
    var expires: Long
)
