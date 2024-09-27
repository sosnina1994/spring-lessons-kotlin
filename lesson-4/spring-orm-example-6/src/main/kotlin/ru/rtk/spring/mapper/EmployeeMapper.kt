package ru.rtk.spring.mapper

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.model.Contact
import ru.rtk.spring.model.Employee

@Service
class EmployeeMapper {
    fun mapToModel(request: CreateEmployeeRequest): Employee {

        return Employee(
            id = null,
            fullName = request.fullName,
            birthDate = request.birthDate,
            contact = mapContact(request)
        )
    }

    private fun mapContact(request: CreateEmployeeRequest): Contact? {
        return if (request.email != null && request.phoneNumber != null) {
            Contact(id = null, email = request.email, phoneNumber = request.phoneNumber)
        } else null
    }

    fun mapToResp(model: Employee?): EmployeeResponse =
        EmployeeResponse(
            id = model?.id,
            fullName = model?.fullName,
            email = model?.contact?.email,
            phoneNumber = model?.contact?.phoneNumber,
            birthDate = model?.birthDate
        )
}