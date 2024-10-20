package ru.rtk.spring.controller.dto

import java.time.LocalDate

data class EmployeeResponse(
    var id: Long? = null,
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var birthDate: LocalDate? = null
)