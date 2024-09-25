package ru.rtk.spring.mapper

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.domain.EquipmentDto
import ru.rtk.spring.model.Contact
import ru.rtk.spring.model.Employee
import ru.rtk.spring.model.Equipment

@Service
class EmployeeMapper {
    fun mapToModel(request: CreateEmployeeRequest): Employee {
        return Employee(
            id = null,
            fullName = request.fullName,
            birthDate = request.birthDate,
            contact = mapContact(request),
            equipments = mapEquipment(request)
        )
    }


    private fun mapContact(request: CreateEmployeeRequest): Contact? {
        return if (request.email != null && request.phoneNumber != null) {
            Contact(id = null, email = request.email, phoneNumber = request.phoneNumber)
        } else null
    }

    private fun mapEquipment(request: CreateEmployeeRequest): Set<Equipment> {
        val set = mutableSetOf<Equipment>()
        request.equipments.forEach{ set.add(Equipment(null, it))}
        return set

    }

    fun mapToResp(model: Employee?): EmployeeResponse =
        EmployeeResponse(
            id = model?.id,
            fullName = model?.fullName,
            email = model?.contact?.email,
            phoneNumber = model?.contact?.phoneNumber,
            birthDate = model?.birthDate,
            euqipments = mapEq(model)
        )

    private fun mapEq(model: Employee?): List<EquipmentDto> {
        val list = mutableListOf<EquipmentDto>()
        model?.equipments?.forEach { equipment -> list.add(EquipmentDto(equipment.id, equipment.name)) }
        return list
    }
}