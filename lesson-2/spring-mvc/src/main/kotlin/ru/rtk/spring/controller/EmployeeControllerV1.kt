package ru.rtk.spring.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.service.EmployeeService

@Controller
class EmployeeControllerV1(val employeeService: EmployeeService) {

    //@PostMapping("api/v1/employees")
    @RequestMapping(path = ["api/v1/employees"], method = [RequestMethod.POST])
    fun create(@RequestBody employeeRequest: CreateEmployeeRequest): ResponseEntity<EmployeeResponse> {
        val resp = employeeService.save(employeeRequest)
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(resp)
    }

    @GetMapping("api/v1/employees/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<EmployeeResponse> {
        val resp = employeeService.getById(id)
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(resp)
    }
}