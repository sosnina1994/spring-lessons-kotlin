package ru.rtk.spring.model

import jakarta.persistence.*
import ru.rtk.spring.emun.StatusType

@Table(name = "statuses")
@Entity
data class EmployeeStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    val statusType: StatusType? = null
)
