package ru.rtk.spring.service.repo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.data.repository.findByIdOrNull
import ru.rtk.spring.model.Contact
import ru.rtk.spring.model.Employee
import ru.rtk.spring.model.History
import java.math.BigDecimal
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
            .ignoringFields(Employee::id.name, "contact.id")
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
        id = 0L,
        fullName = "TESTING",
        birthDate = LocalDate.now(),
        contact = Contact(
            email = "TESTING@mail.ru",
            phoneNumber = "0998999"
        ),
        histories = setOf(
            History(id = 1L, dayCost = BigDecimal(1000).setScale(2), beginDate = LocalDate.of(2024, 1, 9)),
            History(id = 2L, dayCost = BigDecimal(1500).setScale(2), beginDate = LocalDate.of(2024, 3, 1)),
            History(id = 3L, dayCost = BigDecimal(2000).setScale(2), beginDate = LocalDate.of(2024, 6, 1)),
        )
    )
}