package ru.rtk.spring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.rtk.spring.service.PersonService

@RestController
class PersonController(val personService: PersonService) {
    @GetMapping("/")
    fun index(): String {
        return personService.getByName("Ivan").toString()
    }
}