package com.demo.modules.wechat.model.vo

import java.util.*


/**
 *
 * Created by yeqinhua on 2019/1/10.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
data class JSSDKConfig(
    /**  必须这么写，否则wx.invoke调用形式的jsapi会有问题 */
    var beta: Boolean = true,
    /**  是否调试 */
    var debug: Boolean = true,
    /**  微信公众号appid */
    var appId: String? = null,
    /**  时间戳 */
    var timestamp: String? = null,
    /**  随机串 */
    var nonceStr: String? = null,
    /**  签名 */
    var signature: String? = null,
    /**  签名前的数据 */
    var base: TreeMap<String, String>? = null
)
