package com.aegis.modules.system.dao

import com.aegis.modules.system.model.entity.WorkUser
import org.apache.ibatis.annotations.Mapper

/**
 * 企业微信用户接口
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Mapper
interface WorkUserDAO {
  /**
   * 根据用户 username 获取用户
   * @param username 用户名称
   * @return WorkUser?
   */
  fun loadWorkUserByName(username: String): WorkUser?
}
