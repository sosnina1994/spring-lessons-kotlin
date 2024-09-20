package ru.rtk.spring.domain

import java.time.LocalDate

data class UpdateEmployeeRequest (
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null
)