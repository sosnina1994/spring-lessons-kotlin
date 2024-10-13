package ru.rtk.spring.controller.mapper

import ru.rtk.spring.controller.dto.CreateEmployeeRequest
import ru.rtk.spring.controller.dto.EmployeeHistoryDto
import ru.rtk.spring.controller.dto.EmployeeResponse
import ru.rtk.spring.controller.dto.UpdateEmployeeRequest
import ru.rtk.spring.model.Contact
import ru.rtk.spring.model.Employee
import ru.rtk.spring.model.History
import java.math.BigDecimal
import java.time.LocalDate

object EmployeeMapper {
    fun mapToModel(request: CreateEmployeeRequest): Employee =
        Employee(
            fullName = request.fullName.orEmpty(),
            birthDate = request.birthDate ?: LocalDate.now(),
            contact = mapContact(request.email, request.phoneNumber),
            histories = mapHistories(request)
        )

    fun mapToResp(model: Employee?): EmployeeResponse =
        EmployeeResponse(
            id = model?.id,
            fullName = model?.fullName,
            email = model?.contact?.email,
            phoneNumber = model?.contact?.phoneNumber,
            birthDate = model?.birthDate,
            histories = model?.histories?.map {
                EmployeeHistoryDto(
                    beginDate = it.beginDate,
                    dayCost = it.dayCost
                )
            } ?: emptyList()
        )

    fun fromUpdateRequest(employeeDb: Employee, request: UpdateEmployeeRequest) = Employee(
        id = employeeDb.id,
        fullName = request.fullName ?: employeeDb.fullName,
        contact = mapContact(request.email, request.phoneNumber)
    )

    private fun mapContact(email: String?, phoneNumber: String?): Contact? {
        return if (email != null && phoneNumber != null) {
            Contact(email = email, phoneNumber = phoneNumber)
        } else null
    }

    private fun mapHistories(request: CreateEmployeeRequest): Set<History> {
        return request.histories.map {
            History(
                beginDate = it.beginDate ?: LocalDate.now(),
                dayCost = it.dayCost ?: BigDecimal.ZERO
            )
        }.toSet()
    }
}