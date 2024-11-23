package ru.rtk.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class FeignClientExample

fun main(args: Array<String>) {
	runApplication<FeignClientExample>(*args)
}
