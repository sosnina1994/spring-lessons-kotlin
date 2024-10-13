package ru.rtk.spring.dao

import ru.rtk.spring.model.Employee

interface EmployeeDao {
    fun save(employee: Employee): Employee

    fun getById(id: Long): Employee?

    fun getAll(): List<Employee?>

    fun updateById(id: Long, fullName: String): Employee?

    fun deleteById(id: Long)
}