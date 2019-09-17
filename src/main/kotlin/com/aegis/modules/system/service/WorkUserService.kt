package com.aegis.modules.system.service

import com.aegis.modules.system.entity.WorkUser

/**
 * 企业微信用户业务接口
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
interface WorkUserService {
  /**
   * 根据 id 获取企业微信用户
   * @param id t_work_user 主键、
   * @return WorkUser
   */
  fun loadWorkUserByName(name: String): WorkUser?
}