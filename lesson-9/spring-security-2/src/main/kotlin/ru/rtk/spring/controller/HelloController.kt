package ru.rtk.spring.controller

import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/admin", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayHelloForAdmin(): String {
        return "Hello ADMIN!"
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/user", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayHelloFoeUser(): String {
        return "Hello USER!"
    }
}