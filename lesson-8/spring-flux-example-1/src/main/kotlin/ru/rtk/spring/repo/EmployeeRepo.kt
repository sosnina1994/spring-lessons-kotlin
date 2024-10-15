package ru.rtk.spring.repo

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ru.rtk.spring.model.Employee


interface EmployeeRepo: ReactiveCrudRepository<Employee, Long> {
}