package ru.rtk.spring.model

import jakarta.persistence.*

@Table(name = "contacts") // Задает имя таблицы, на которую будет отображаться сущность
@Entity // Указывает, что данный класс является сущностью
data class Contact(

    // Позволяет указать какое поле является идентификатором
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    // Задает имя и некоторые свойства поля таблицы, на которое
    // будет отображаться поле сущности
    @Column(name = "email", nullable = false)
    var email: String? = null,

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String? = null
)
