package ru.rtk.spring.domain

data class UpdateEmployeeRequest (
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var skillIds: MutableList<Long> = mutableListOf()
)