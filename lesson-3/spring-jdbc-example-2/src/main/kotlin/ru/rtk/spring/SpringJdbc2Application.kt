package ru.rtk.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJdbc2Application

fun main(args: Array<String>) {
	runApplication<SpringJdbc2Application>(*args)

	// сваггер
	// http://localhost:8080/swagger-ui/index.html#
	// http://localhost:8080/api/v1/employees/1

}
