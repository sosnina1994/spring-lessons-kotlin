package ru.rtk.spring.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table(name = "EMPLOYEES")
data class Employee(
    @Id
    @Column
    val id: Long = 0L,

    @Column(value = "FULL_NAME")
    val fullName: String = "",

    @Column(value = "EMAIL")
    val email: String? = null,

    @Column(value = "PHONE_NUMBER")
    val phoneNumber: String = "",

    @Column(value = "BIRTH_DATE")
    val birthDate: LocalDate = LocalDate.now()
)