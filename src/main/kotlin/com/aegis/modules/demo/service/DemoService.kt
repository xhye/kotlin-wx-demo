package com.aegis.modules.demo.service

import com.aegis.modules.demo.model.entity.Demo
import com.github.pagehelper.PageInfo

/**
 * {msg}
 * Created by {creator} on {create date}.
 *
 * @author {creator}
 * @since 0.0.1
 */
interface DemoService {
  /** {msg}
   * @param name {解释}
   * @return PageInfo<Demo>
   */
  fun list(name: String?): PageInfo<Demo>
}