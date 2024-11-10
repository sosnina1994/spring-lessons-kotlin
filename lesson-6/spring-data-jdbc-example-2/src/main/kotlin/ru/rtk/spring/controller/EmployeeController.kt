package ru.rtk.spring.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.rtk.spring.controller.dto.CreateEmployeeRequest
import ru.rtk.spring.controller.dto.EmployeeResponse
import ru.rtk.spring.controller.dto.UpdateEmployeeRequest
import ru.rtk.spring.controller.mapper.EmployeeMapper
import ru.rtk.spring.service.EmployeeService
import java.math.BigDecimal

@RestController
class EmployeeController(private val employeeService: EmployeeService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees")
    fun create(@RequestBody employeeRequest: CreateEmployeeRequest): EmployeeResponse {
        val employee = EmployeeMapper.mapToModel(employeeRequest)
        val savedEmployee = employeeService.save(employee)
        return EmployeeMapper.mapToResp(savedEmployee)
    }

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Long): EmployeeResponse {
        return EmployeeMapper.mapToResp(employeeService.getById(id))
    }

    @GetMapping("/employees")
    fun findAll(): List<EmployeeResponse> {
        return employeeService.getAll().map { EmployeeMapper.mapToResp(it) }
    }

    @PatchMapping("/employees/{id}")
    fun updateById(@PathVariable id: Long, @RequestBody request: UpdateEmployeeRequest): EmployeeResponse {
        val employeeDb = employeeService.getById(id)
        val employee = employeeService.save(EmployeeMapper.fromUpdateRequest(employeeDb, request))
        return EmployeeMapper.mapToResp(employee)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/employees/{id}")
    fun deleteById(@PathVariable id: Long) {
        employeeService.delete(id)
    }

    @GetMapping("/employees/by-day-cost")
    fun findAllByDayCost(@RequestParam dayCost: BigDecimal): List<EmployeeResponse> {
        return employeeService.findAllByDayCost(dayCost).map { EmployeeMapper.mapToResp(it) }
    }
}