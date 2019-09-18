package com.aegis.modules.qywork.model.entiry


/**
 * ApiTicket
 * Created by 程格聪 on 2018/12/19.
 *
 * @author 程格聪
 * @since 0.0.1
 */
data class ApiTicket(
    /**  token字符串 */
    var ticket: String,
    /**  过期时间 */
    var expires: Long
)
