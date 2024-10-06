package ru.rtk.spring.domain

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateContactDto (
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email address is not valid")
    var email: String? = null,
    var phoneNumber: String? = null
)
