package ru.rtk.spring.model

import java.time.LocalDate

data class Employee(
    var id: Long? = null,
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var birthDate: LocalDate? = null
)