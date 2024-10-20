package ru.rtk.spring.controller.dto

import java.math.BigDecimal
import java.time.LocalDate

data class CreateEmployeeRequest(
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var birthDate: LocalDate? = null,
    val histories: List<EmployeeHistoryDto> = emptyList()
)

data class EmployeeHistoryDto(
    val beginDate: LocalDate? = null,
    val dayCost: BigDecimal? = null
)