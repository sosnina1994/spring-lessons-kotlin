package ru.rtk.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringFlux1Application

fun main(args: Array<String>) {
	runApplication<SpringFlux1Application>(*args)

	// сваггер
	// http://localhost:8080/swagger-ui/index.html#


}
