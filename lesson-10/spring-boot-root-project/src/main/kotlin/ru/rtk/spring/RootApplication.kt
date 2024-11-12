package ru.rtk.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RootApplication

fun main(args: Array<String>) {
	runApplication<RootApplication>(*args)
}
