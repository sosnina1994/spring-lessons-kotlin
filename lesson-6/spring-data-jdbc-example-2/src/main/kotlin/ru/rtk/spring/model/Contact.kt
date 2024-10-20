package ru.rtk.spring.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


@Table(name = "CONTACTS")
data class Contact(
    @Id
    @Column
    val id: Long? = null,

    @Column(value = "EMAIL")
    val email: String = "",

    @Column(value = "PHONE_NUMBER")
    val phoneNumber: String = "",
)
