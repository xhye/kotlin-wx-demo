package com.aegis.modules.demo.controller

import com.aegis.bean.Response
import com.aegis.kotlin.successWithData
import com.aegis.modules.demo.service.DemoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * demo
 * Created by {creator} on {create date}.
 *
 * @author {creator}
 * @since 0.0.1
 */
@RestController
@RequestMapping("demo")
class TestActon(private val demoService: DemoService) {
    @GetMapping("/get")
    fun get(): Response<Any> {
        return successWithData(" get success")
    }
    @PostMapping("/post")
    fun post(): Response<Any> {
        return successWithData(" post success")
    }
    @GetMapping("/list")
    fun list(name: String?): Response<Any> {
        return successWithData(demoService.list(name))
    }
}