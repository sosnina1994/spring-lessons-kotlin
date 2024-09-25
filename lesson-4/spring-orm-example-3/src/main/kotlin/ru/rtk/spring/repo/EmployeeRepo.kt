package ru.rtk.spring.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.rtk.spring.model.Employee


interface EmployeeRepo: JpaRepository<Employee, Long> {
}