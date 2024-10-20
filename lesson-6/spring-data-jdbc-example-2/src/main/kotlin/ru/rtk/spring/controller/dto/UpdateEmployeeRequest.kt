package ru.rtk.spring.controller.dto

data class UpdateEmployeeRequest (
    var fullName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null
)