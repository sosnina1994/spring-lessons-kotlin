package ru.rtk.spring.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.rtk.spring.model.EmployeeStatus

interface StatusRepo: JpaRepository<EmployeeStatus, Long> {
}