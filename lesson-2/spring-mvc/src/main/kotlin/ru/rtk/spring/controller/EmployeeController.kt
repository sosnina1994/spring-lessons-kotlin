package ru.rtk.spring.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.domain.EmployeesResponse
import ru.rtk.spring.domain.UpdateEmployeeRequest
import ru.rtk.spring.service.EmployeeService

@RestController
class EmployeeController(private val employeeService: EmployeeService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees")
    fun create(@RequestBody employeeRequest: CreateEmployeeRequest): EmployeeResponse {
        return employeeService.save(employeeRequest)
    }

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Long): EmployeeResponse {
        return employeeService.getById(id)
    }

    @GetMapping("/employees")
    fun findAll(): EmployeesResponse {
        return employeeService.getAll()
    }

    @PatchMapping("/employees/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody request: UpdateEmployeeRequest): EmployeeResponse {
        return employeeService.getById(id)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/employees/{id}")
    fun deleteById(@PathVariable id: Long) {
        employeeService.delete(id)
    }
}