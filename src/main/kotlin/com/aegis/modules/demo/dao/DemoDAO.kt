package com.aegis.modules.demo.dao

import com.aegis.modules.demo.model.entity.Demo
import org.apache.ibatis.annotations.Mapper

/**
 * {msg}
 * Created by {creator} on {create date}.
 *
 * @author {creator}
 * @since 0.0.1
 */
@Mapper
interface DemoDAO {

  fun list(): List<Demo>
}
