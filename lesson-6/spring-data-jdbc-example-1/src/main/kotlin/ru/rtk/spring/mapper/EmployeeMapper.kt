package ru.rtk.spring.mapper

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.model.Employee

@Service
class EmployeeMapper {
    fun mapToModel(request: CreateEmployeeRequest): Employee =
        Employee(
            fullName = request.fullName,
            email = request.email,
            phoneNumber = request.phoneNumber,
            birthDate = request.birthDate
        )

    fun mapToResp(model: Employee?): EmployeeResponse =
        EmployeeResponse(
            id = model?.id,
            fullName = model?.fullName,
            email = model?.email,
            phoneNumber = model?.phoneNumber,
            birthDate = model?.birthDate
        )
}