package com.aegis.modules.qywork.service.impl

import com.aegis.modules.qywork.service.QYUserService
import com.aegis.modules.qywork.service.face.QYUserBusiness
import com.aegis.modules.qywork.utils.QYWorkUtils
import org.springframework.stereotype.Service

/**
 * 企业微信用户相关接口
 * Created by yeqinhua on 2019/8/20.
 * @author yeqinhua
 * @since 0.0.1
 */
@Service
class QYUserServiceImpl(private val wechatUtils: QYWorkUtils, private val qyUserBusiness: QYUserBusiness) : QYUserService {
  override fun userId2User(userId: String): MutableMap<String, Any> {
    val result = qyUserBusiness.userIdToUser(userId, wechatUtils.getAccessToken())
    return if (result["errcode"].toString().toInt() == 0) {
      result
    } else {
      mutableMapOf()
    }
  }

  override fun codeToUserId(code: String): MutableMap<String, Any> {
    val result = qyUserBusiness.codeToUserId(code, wechatUtils.getAccessToken())
//    {DeviceId=cd9e459ea708a948d5c2f5a6ca8838cf, errcode=0, errmsg=ok, UserId=USERID} // 企业用户
//    {DeviceId=cd9e459ea708a948d5c2f5a6ca8838cf, errcode=0, errmsg=ok, OpenId=OpenId} // 非企业用户
    val final = mutableMapOf<String, Any>()
    if (result["errcode"].toString().toInt() == 0) {
      final["deviceId"] = result["DeviceId"].toString() // 手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)
      if (result["UserId"] != null) {
        final["isQYUser"] = true // 是企业用户
        final["userId"] = result["UserId"].toString() // 企业用户Id
      } else if (result["OpenId"] != null) {
        final["isQYUser"] = false // 不是企业用户
        final["openId"] = result["OpenId"].toString() // 非企业成员的标识，对当前企业唯一
      }
    }
    return final
  }
}