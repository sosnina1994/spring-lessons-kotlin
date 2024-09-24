package ru.rtk.spring.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.Import
import ru.rtk.spring.model.Employee
import java.time.LocalDate

@DisplayName("Репозиторий на основе Jdbc")
@JdbcTest
@Import(EmployeeDaoImpl::class)
internal class EmployeeDaoImplTest @Autowired constructor(
    private val repositoryJdbc: EmployeeDaoImpl
) {

    @Test
    fun save() {

        val employee = getExample()
        val returnedEmployee = repositoryJdbc.save(employee)

        assertThat(returnedEmployee).isNotNull()
            .matches { it.id!! > 0 }
            .usingRecursiveComparison()
            .ignoringExpectedNullFields()
            .isEqualTo(employee)

        assertThat(repositoryJdbc.getById(returnedEmployee.id!!))
            .isPresent()
            .get()
            .isEqualTo(returnedEmployee)

    }

    @Test
    fun getById() {
        val actualEmployee = repositoryJdbc.getById(1L)
        assertThat(actualEmployee)
            .isPresent()
            .get()
            .matches { it.id!! == 1L }
    }

    @Test
    fun getAll() {
        val employees = repositoryJdbc.getAll()
        assertThat(employees)
            .isNotEmpty()
    }

    @Test
    fun updateById() {
        val actualEmployee = repositoryJdbc.getById(1L)
        assertThat(actualEmployee)
            .isPresent()
            .get()
            .matches { it.id!! == 1L }

        val updatedEmployee = repositoryJdbc.updateById(1L, "test")

        val newEmployee = repositoryJdbc.getById(1L)

        assertThat(newEmployee)
            .isPresent()
            .get()
            .isEqualTo(updatedEmployee)
            .isNotEqualTo(actualEmployee)

        println(actualEmployee.get().fullName)
        println(newEmployee.get().fullName)
    }

    @Test
    fun deleteById() {
        assertThat(repositoryJdbc.getById(1L)).isPresent()
        repositoryJdbc.deleteById(1L)
        assertThat(repositoryJdbc.getById(1L)).isEmpty()
    }


    private fun getExample() = Employee(
        0, "name123", "name123@mail.ru", "90887",
        LocalDate.of(1990, 1, 1)
    )
}