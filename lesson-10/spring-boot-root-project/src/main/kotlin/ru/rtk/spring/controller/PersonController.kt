package ru.rtk.spring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.rtk.spring.domain.Person

@RestController
class PersonController {
    @GetMapping("/")
    fun get(): Person {
        println("im here")
        return Person(name = "НЕ иван", age = 30)
    }
}