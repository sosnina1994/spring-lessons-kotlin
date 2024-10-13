package ru.rtk.spring.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table(name = "EMPLOYEE_HISTORIES")
data class History(
    @Id
    @Column
    val id: Long? = null,

    @Column(value = "BEGIN_DATE")
    val beginDate: LocalDate = LocalDate.now(),

    @Column(value = "DAY_COST")
    val dayCost: BigDecimal = BigDecimal.ZERO
)