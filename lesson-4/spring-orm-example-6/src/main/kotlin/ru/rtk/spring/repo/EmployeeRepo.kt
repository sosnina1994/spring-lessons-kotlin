package ru.rtk.spring.repo

import ru.rtk.spring.model.Employee
import java.util.*


interface EmployeeRepo{

    fun findById(id: Long): Optional<Employee>

    fun findAll(): List<Employee>

    fun save(employee: Employee): Employee

    fun deleteById(id: Long)
}