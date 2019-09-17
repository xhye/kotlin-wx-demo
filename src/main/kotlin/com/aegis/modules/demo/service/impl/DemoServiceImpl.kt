package com.aegis.modules.demo.service.impl

import com.aegis.modules.demo.dao.DemoDAO
import com.aegis.modules.demo.model.entity.Demo
import com.aegis.modules.demo.service.DemoService
import com.github.pagehelper.PageInfo
import com.github.pagehelper.PageHelper
import org.springframework.stereotype.Service


/**
 * {msg}
 * Created by {creator} on {create date}.
 *
 * @author {creator}
 * @since 0.0.1
 */
@Service
class DemoServiceImpl(private val demoDAO: DemoDAO) : DemoService {
  override fun list(name: String?): PageInfo<Demo> {
    val pageInfo = PageHelper.startPage<Demo>(1, 10).doSelectPageInfo<Demo> { demoDAO.list() }
    println(pageInfo)
    return pageInfo
  }
}