package ru.rtk.spring.domain

import java.time.LocalDate

data class EmployeeResponse(
    var id: Long? = null,
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var birthDate: LocalDate? = null,
    var euqipments: List<EquipmentDto> = mutableListOf(),
    var status: EmployeeStatusDto? = null,
    var skills: List<SkillDto> = mutableListOf(),
)