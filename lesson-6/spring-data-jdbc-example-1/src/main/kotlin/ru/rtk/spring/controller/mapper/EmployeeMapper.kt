package ru.rtk.spring.controller.mapper

import ru.rtk.spring.controller.dto.CreateEmployeeRequest
import ru.rtk.spring.controller.dto.EmployeeResponse
import ru.rtk.spring.controller.dto.UpdateEmployeeRequest
import ru.rtk.spring.model.Employee
import java.time.LocalDate

object EmployeeMapper {
    fun mapToModel(request: CreateEmployeeRequest): Employee =
        Employee(
            fullName = request.fullName.orEmpty(),
            email = request.email,
            phoneNumber = request.phoneNumber.orEmpty(),
            birthDate = request.birthDate ?: LocalDate.now()
        )

    fun mapToResp(model: Employee?): EmployeeResponse =
        EmployeeResponse(
            id = model?.id,
            fullName = model?.fullName,
            email = model?.email,
            phoneNumber = model?.phoneNumber,
            birthDate = model?.birthDate
        )

    fun fromUpdateRequest(employeeDb: Employee, request: UpdateEmployeeRequest)= Employee(
        id = employeeDb.id,
        fullName = request.fullName ?: employeeDb.fullName,
        email = request.email ?: employeeDb.email,
        phoneNumber = request.phoneNumber ?: employeeDb.phoneNumber
    )
}