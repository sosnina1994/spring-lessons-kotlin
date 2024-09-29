package ru.rtk.spring.domain

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate

data class CreateEmployeeRequest(
    @field:NotBlank(message = "fullName is not null or empty")
    @field:Pattern(regexp = "[а-яА-Я]*", message = "Name is not valid")
    var fullName: String? = null,

    var birthDate: LocalDate? = null,

    @field:Valid
    var contact: CreateContactDto? = null
)