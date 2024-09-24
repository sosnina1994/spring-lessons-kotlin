package ru.rtk.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringOrm1Application

fun main(args: Array<String>) {
	runApplication<SpringOrm1Application>(*args)

	// сваггер
	// http://localhost:8080/swagger-ui/index.html#


}
