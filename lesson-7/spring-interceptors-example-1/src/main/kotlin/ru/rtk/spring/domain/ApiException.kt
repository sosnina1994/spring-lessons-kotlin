package ru.rtk.spring.domain

import org.springframework.http.HttpStatus

data class ApiException(
    val details: List<String>,
    val status: HttpStatus,
)