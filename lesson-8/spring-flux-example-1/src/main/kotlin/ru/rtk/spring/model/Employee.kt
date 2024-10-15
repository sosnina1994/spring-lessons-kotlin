package ru.rtk.spring.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("employees")
data class Employee(
    @Id
    var id: Long? = null,

    @Column( "full_name")
    var fullName: String? = null,

    @Column("birth_date")
    var birthDate: LocalDate? = null,

    @Column("email")
    var email: String? = null,

    @Column("phone_number")
    var phone: String? = null,
)