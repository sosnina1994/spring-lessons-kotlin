package ru.rtk.spring.repo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import
import ru.rtk.spring.model.Contact
import ru.rtk.spring.model.Employee
import java.time.LocalDate
import java.util.stream.IntStream

@DataJpaTest
@Import(JpaEmployeeRepository::class)
class JpaEmployeeRepositoryTest @Autowired constructor(
    private val employeeRepository: JpaEmployeeRepository,
    private val em: TestEntityManager
) {

    @Test
    fun findById() {
        val expectedEmployees: List<Employee> = getDbEmployees()
        for (employee in expectedEmployees) {
            val employeeOptional = employeeRepository.findById(employee.id!!)
            assertThat(employeeOptional).isPresent()
                .get()
                .isEqualTo(employee)
        }
    }

    @Test
    fun save() {
        val employee = createTestEmployee()
        em.merge(employee)

        employeeRepository.save(employee)
        em.detach(employee)

        assertThat(employee.id).isGreaterThan(0)

        val findEmployee = em.find(Employee::class.java, employee.id)
        assertThat(findEmployee)
            .usingRecursiveComparison()
            .isEqualTo(employee)
    }

    @Test
    fun deleteById() {
        val employee = em.find(Employee::class.java, 1L)
        assertThat(employee).isNotNull()

        employeeRepository.deleteById(1L)

        val notFoundEmployee = em.find(Employee::class.java, 1L)
        assertThat(notFoundEmployee).isNull()
    }

    private fun getDbEmployees(): MutableList<Employee> {
        return IntStream.range(1, 2).boxed()
            .map { id: Int? -> em.find(Employee::class.java, id) }
            .toList()
    }

    private fun createTestEmployee() = Employee(
        id = null,
        fullName = "TESTING",
        birthDate = LocalDate.now(),
        contact = Contact(
            id = null,
            email = "TESTING@mail.ru", phoneNumber = "0998999"
        )
    )
}