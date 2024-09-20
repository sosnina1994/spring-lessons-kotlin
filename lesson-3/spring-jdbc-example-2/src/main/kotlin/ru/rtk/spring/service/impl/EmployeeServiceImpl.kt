package ru.rtk.spring.service.impl

import org.springframework.stereotype.Service
import ru.rtk.spring.dao.EmployeeDao
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.domain.EmployeesResponse
import ru.rtk.spring.domain.UpdateEmployeeRequest
import ru.rtk.spring.mapper.EmployeeMapper
import ru.rtk.spring.service.EmployeeService

@Service
class EmployeeServiceImpl(
    private val employeeDao: EmployeeDao,
    private val mapper: EmployeeMapper
) : EmployeeService {

    override fun save(request: CreateEmployeeRequest): EmployeeResponse {
        val employee = mapper.mapToModel(request)
        val entity = employeeDao.save(employee)
        return mapper.mapToResp(entity)
    }

    override fun getById(id: Long): EmployeeResponse {
        val entity = employeeDao.getById(id)
        return mapper.mapToResp(entity.get())
    }

    override fun getAll(): EmployeesResponse {
        val entities = employeeDao.getAll()

        val employeeResponses = ArrayList<EmployeeResponse>()

        entities.stream().forEach { employeeResponses.add(mapper.mapToResp(it)) }
        return EmployeesResponse(employeeResponses)
    }

    override fun updateById(id: Long, request: UpdateEmployeeRequest): EmployeeResponse {
        val entity = employeeDao.updateById(id, request.fullName!!)
        return mapper.mapToResp(entity!!)
    }

    override fun delete(id: Long) {
        employeeDao.deleteById(id)
    }

}