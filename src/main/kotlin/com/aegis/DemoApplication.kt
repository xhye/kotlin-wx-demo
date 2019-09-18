package com.aegis

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

/**
 * 启动类
 */
@SpringBootApplication(exclude = [RabbitAutoConfiguration::class]) // 不启动ribbitmq
@EnableFeignClients(basePackages = ["com.aegis.modules.qywork.service"]) // Feign  扫描
@MapperScan(basePackages = ["com.aegis.modules.*.dao"]) // dao 扫描
open class DemoApplication

fun main(args: Array<String>) {
  runApplication<DemoApplication>(*args)
}
