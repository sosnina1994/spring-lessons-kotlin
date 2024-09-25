package ru.rtk.spring.service.impl

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.domain.EmployeesResponse
import ru.rtk.spring.domain.UpdateEmployeeRequest
import ru.rtk.spring.exception.NotFoundException
import ru.rtk.spring.mapper.EmployeeMapper
import ru.rtk.spring.repo.EmployeeRepo
import ru.rtk.spring.repo.StatusRepo
import ru.rtk.spring.service.EmployeeService

@Service
class EmployeeServiceImpl(
    private val repo: EmployeeRepo,
    private val statusRepo: StatusRepo,
    private val mapper: EmployeeMapper
) : EmployeeService {

    override fun save(request: CreateEmployeeRequest): EmployeeResponse {
        val employee = mapper.mapToModel(request)

        val status = statusRepo.findById(request.statusId!!).orElseThrow {NotFoundException("Status not found") }
        employee.status = status

        val entity = repo.save(employee)
        return mapper.mapToResp(entity)
    }

    override fun getById(id: Long): EmployeeResponse {
        val entity = repo.findById(id).orElseThrow { NotFoundException("Employee not found") }
        return mapper.mapToResp(entity)
    }

    override fun getAll(): EmployeesResponse {
        val entities = repo.findAll()

        val employeeResponses = ArrayList<EmployeeResponse>()

        entities.stream().forEach { employeeResponses.add(mapper.mapToResp(it)) }
        return EmployeesResponse(employeeResponses)
    }

    override fun updateById(id: Long, request: UpdateEmployeeRequest): EmployeeResponse {
        var entity = repo.findById(id).orElseThrow { NotFoundException("Employee not found") }
        entity.fullName = request.fullName
        entity.contact?.email = request.email
        entity.contact?.phoneNumber = request.phoneNumber

        val res = repo.save(entity)
        return mapper.mapToResp(res)
    }

    override fun delete(id: Long) {
        repo.deleteById(id)
    }

}