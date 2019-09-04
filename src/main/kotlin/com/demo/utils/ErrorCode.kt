package com.demo.utils

/**
 *
 * Created by 吴昊 on 2018/8/8.
 *
 * @author 吴昊
 * @since
 */
enum class ErrorCode(val code: Int, val zhName: String) {

  AccessDenied(40000, "拒绝访问"),
  AccessTokenInvalid(40001, "非法的Access Token"),
  LoginExpired(40002, "登录超时"),
  OperationInvalid(40003, "非法操作"),
  ResponseTimeout(40004,"响应超时"),
  UnknownError(40005,"未知错误"),
  BadCredential(40006,"认证信息错误"),

  SaveFailed(40501, "保存失败"),
  DeleteFailed(40502, "删除失败"),
  UpdateFailed(40503, "更新失败"),
  QueryFailed(40504, "查询失败"),
  UploadFailed(40505, "上传失败");

}