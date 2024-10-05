package ru.rtk.spring.service

import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.domain.EmployeesResponse
import ru.rtk.spring.domain.UpdateEmployeeRequest

interface EmployeeService {
    fun save(request: CreateEmployeeRequest): EmployeeResponse

    fun getById(id: Long): EmployeeResponse

    fun getAll(): EmployeesResponse

    fun updateById(id: Long, request: UpdateEmployeeRequest): EmployeeResponse

    fun delete(id: Long)
}