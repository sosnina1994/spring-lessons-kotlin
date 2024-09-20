package ru.rtk.spring.dao

import org.springframework.stereotype.Service
import ru.rtk.spring.model.Employee

@Service
class EmployeeDaoImpl(
    private val employeeMock: ArrayList<Employee>
) : EmployeeDao {
    override fun save(employee: Employee): Employee {
        val id = employeeMock.size + 1
        employee.id = id.toLong()
        employeeMock.add(employee)
        return employee
    }

    override fun getById(id: Long): Employee? {
        return employeeMock.stream().filter { employee -> employee.id == id }.findFirst().orElse(null)
    }

    override fun getAll(): List<Employee?> {
        return employeeMock;
    }

    override fun updateById(id: Long, fullName: String): Employee? {
        val entity = employeeMock.stream().filter { employee -> employee.id == id }
            .findFirst()
            .orElse(null)

        if (entity != null) entity.fullName = fullName
        return entity
    }

    override fun deleteById(id: Long) {
        val entity = employeeMock.stream().filter { employee -> employee.id == id }
            .findFirst()
            .orElse(null)

        employeeMock.remove(entity)
    }
}