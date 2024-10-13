package ru.rtk.spring.controller.dto

import java.time.LocalDate

data class CreateEmployeeRequest(
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var birthDate: LocalDate? = null
)