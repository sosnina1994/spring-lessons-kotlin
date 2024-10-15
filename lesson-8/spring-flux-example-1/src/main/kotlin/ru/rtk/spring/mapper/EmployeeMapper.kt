package ru.rtk.spring.mapper

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.model.Employee

@Service
class EmployeeMapper {
    fun mapToEmployee(request: CreateEmployeeRequest): Employee {

        return Employee(
            id = null,
            fullName = request.fullName,
            birthDate = request.birthDate,
            email = request.email,
            phone = request.phoneNumber
        )
    }

    fun mapToResp(model: Employee?): EmployeeResponse =
        EmployeeResponse(
            id = model?.id,
            fullName = model?.fullName,
            email = model?.email,
            phoneNumber = model?.phone,
            birthDate = model?.birthDate
        )
}