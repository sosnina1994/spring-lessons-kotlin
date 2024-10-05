package ru.rtk.spring.mapper

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.CreateContactDto
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
            contact = mapContact(request.contact!!)
        )
    }

    private fun mapContact(contact: CreateContactDto): Contact? {
        return if (contact.email != null && contact.phoneNumber != null) {
            Contact(id = null, email = contact.email, phoneNumber = contact.phoneNumber)
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