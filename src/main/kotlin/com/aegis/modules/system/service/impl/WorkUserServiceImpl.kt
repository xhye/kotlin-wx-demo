package com.aegis.modules.system.service.impl

import com.aegis.modules.system.dao.WorkUserDAO
import com.aegis.modules.system.entity.WorkUser
import com.aegis.modules.system.service.WorkUserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

/**
 * 企业微信用户业务接口实现
 * Created by yeqinhua on 2019/9/16.
 *
 * @author yeqinhua
 * @since 0.0.1
 */
@Service
class WorkUserServiceImpl(private val workUserDAO: WorkUserDAO) : WorkUserService {
  override fun loadWorkUserByName(name: String): WorkUser? {
    return workUserDAO.loadWorkUserByName(name)
  }
}