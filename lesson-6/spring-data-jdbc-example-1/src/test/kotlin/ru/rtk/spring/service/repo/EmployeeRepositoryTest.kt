package ru.rtk.spring.service.repo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.data.repository.findByIdOrNull
import ru.rtk.spring.model.Employee
import java.time.LocalDate

@DisplayName("Репозиторий на основе data-jdbc")
@DataJdbcTest
internal class EmployeeRepositoryTest {

    @Autowired
    private lateinit var repository: EmployeeRepository

    @Test
    fun save() {

        val employee = getExample()
        val returnedEmployee = repository.save(employee)

        assertThat(returnedEmployee).isNotNull
            .matches { it.id > 0 }
            .usingRecursiveComparison()
            .ignoringFields(Employee::id.name)
            .isEqualTo(employee)

        assertThat(repository.findByIdOrNull(returnedEmployee.id)).isEqualTo(returnedEmployee)

    }

    @Test
    fun getById() {
        val actualEmployee = repository.findByIdOrNull(1L)
        assertThat(actualEmployee).matches { it?.id == 1L }
    }

    @Test
    fun getAll() {
        val employees = repository.findAll()
        assertThat(employees).isNotEmpty
    }

    @Test
    fun deleteById() {
        assertThat(repository.findById(1L)).isPresent
        repository.deleteById(1L)
        assertThat(repository.findById(1L)).isEmpty
    }


    private fun getExample() = Employee(
        0, "name123", "name123@mail.ru", "90887",
        LocalDate.of(1990, 1, 1)
    )
}