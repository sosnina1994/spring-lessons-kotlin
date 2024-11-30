package ru.rtk.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class RestClientExample

fun main(args: Array<String>) {
	runApplication<RestClientExample>(*args)
}
