package ru.rtk.spring.domain

import ru.rtk.spring.emun.StatusType

data class EmployeeStatusDto(
    val id: Long? = null,
    val statusType: StatusType? = null
)
