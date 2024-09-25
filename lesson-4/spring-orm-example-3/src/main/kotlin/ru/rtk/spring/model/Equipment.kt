package ru.rtk.spring.model

import jakarta.persistence.*

@Table(name = "equipments")
@Entity
data class Equipment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null

)
