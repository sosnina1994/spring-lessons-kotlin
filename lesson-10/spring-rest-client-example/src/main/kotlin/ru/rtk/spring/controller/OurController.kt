package ru.rtk.spring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.rtk.spring.domain.Person
import ru.rtk.spring.service.OurService

@RestController
class OurController(private val ourService: OurService) {
    @GetMapping("/")
    fun get(): Person? {
        return ourService.getUser()
    }
}