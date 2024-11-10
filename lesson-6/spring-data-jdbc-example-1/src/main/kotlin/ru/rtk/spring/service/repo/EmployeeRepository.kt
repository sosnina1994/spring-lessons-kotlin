package ru.rtk.spring.service.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.rtk.spring.model.Employee

@Repository
interface EmployeeRepository: CrudRepository<Employee, Long>