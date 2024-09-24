package ru.rtk.spring.dao

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import ru.rtk.spring.model.Employee
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Repository
class EmployeeDaoImpl(
    private val jdbc: NamedParameterJdbcOperations

) : EmployeeDao {
    override fun save(employee: Employee): Employee {

        val keyHolder = GeneratedKeyHolder()

        val parameterSource = MapSqlParameterSource()
        parameterSource.addValue("full_name", employee.fullName)
        parameterSource.addValue("email", employee.email)
        parameterSource.addValue("phone_number",employee.phoneNumber)
        parameterSource.addValue("birth_date",employee.birthDate)

        jdbc.update(
            """
                insert into EMPLOYEES (full_name, email, phone_number, birth_date) 
                values (:full_name, :email, :phone_number, :birth_date)
                """,
            parameterSource,
            keyHolder,
            arrayOf("id")
        )
        employee.id = keyHolder.key!!.toLong()
        return employee
    }

    override fun getById(id: Long): Optional<Employee> {
        val employee = jdbc.query(
            """
                SELECT e.id, e.full_name, e.email, e.phone_number, e.birth_date
                FROM EMPLOYEES e 
                WHERE e.id = :id
                """.trimIndent(),
            mapOf("id" to id),
            mapper
        )

        if (employee.size == 1) {
            return Optional.of(employee[0])
        }

        return Optional.empty()
    }

    override fun getAll(): List<Employee?> {
        return jdbc.query(
            """
               SELECT e.id, e.full_name, e.email, e.phone_number, e.birth_date
               FROM EMPLOYEES e
               """.trimIndent(),
            mapper
        )
    }

    override fun updateById(id: Long, fullName: String): Employee? {

        val res = jdbc.update(
            """
                UPDATE EMPLOYEES 
                SET full_name = :full_name
                WHERE id = :id
                """.trimIndent(),
            mapOf(
                "id" to id,
                "full_name" to fullName,
            )
        )
        return getById(id).get()
    }

    override fun deleteById(id: Long) {
        jdbc.update(
            "DELETE FROM EMPLOYEES WHERE id = :id",
            mapOf("id" to id),
        )
    }

    val mapper = RowMapper<Employee> { rs: ResultSet, rowNum: Int ->
        Employee(
            id = rs.getString("id").toLong(),
            fullName = rs.getString("full_name"),
            email = rs.getString("email"),
            phoneNumber = rs.getString("phone_number"),
            birthDate = LocalDate.parse(
                rs.getString("birth_date"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )
        )
    }


}