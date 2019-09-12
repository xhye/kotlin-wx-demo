package com.demo.modules.demo.controller

import com.demo.utils.Response
import com.demo.utils.successWithData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * demo
 */
@RestController
@RequestMapping("demo")
class TestActon {
    @GetMapping("/get")
    fun get(): Response<Any> {
        return successWithData(" get success")
    }
    @PostMapping("/post")
    fun post(): Response<Any> {
        return successWithData(" post success")
    }
}