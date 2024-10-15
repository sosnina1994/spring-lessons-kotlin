package ru.rtk.spring.domain

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate

data class CreateEmployeeRequest(

    @field:NotBlank(message = "test")
    var fullName: String? = null,

    var birthDate: LocalDate? = null,

    var email: String? = null,

    var phoneNumber: String? = null
)