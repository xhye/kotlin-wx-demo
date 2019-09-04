package com.demo.modules.wechat.exception

import com.demo.modules.wechat.model.vo.BaseVO


/**
 * 企业微信微信接口返回异常
 * Created by yeqinhua on 2019/8/20.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
open class QYException(baseVO: BaseVO) : Exception(baseVO.errmsg)
