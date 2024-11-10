package ru.rtk.spring.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.rtk.spring.model.Employee
import ru.rtk.spring.service.repo.EmployeeRepository
import java.math.BigDecimal

@Service
class EmployeeService(
    private val repository: EmployeeRepository,
) {

    fun save(employee: Employee): Employee = repository.save(employee)

    fun getById(id: Long): Employee {
        return repository.findByIdOrNull(id) ?: throw NoSuchElementException("Сотрудник с $id не найден")
    }

    fun getAll(): List<Employee> = repository.findAll().toList()

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun findAllByDayCost(dayCost: BigDecimal): List<Employee> {
        return repository.findAllByHistoriesDayCost(dayCost)
    }

}