package ru.rtk.spring.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import ru.rtk.spring.model.Employee
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Repository
class EmployeeDaoImpl(
    private val jdbc: JdbcTemplate

) : EmployeeDao {
    override fun save(employee: Employee): Employee {
        val rowCount = count()
        employee.id = rowCount.toLong() + 1

        jdbc.update(
            "insert into EMPLOYEES (id, full_name, email, phone_number, birth_date) values (?, ?, ?, ?, ?)",
            employee.id, employee.fullName, employee.email, employee.phoneNumber, employee.birthDate
        )
        return employee
    }

    override fun getById(id: Long): Employee? {
        return jdbc.queryForObject(
            "select id, full_name, email, phone_number, birth_date from EMPLOYEES e where e.id=?",
            mapper,
            id
        )
    }

    override fun getAll(): List<Employee?> {
        return jdbc.query("select id, full_name, email, phone_number, birth_date from EMPLOYEES", mapper)
    }

    override fun updateById(id: Long, fullName: String): Employee? {
        jdbc.update(
            "update EMPLOYEES e set full_name = ? where e.id = ?", fullName, id
        )

        return jdbc.queryForObject(
            "select id, full_name, email, phone_number, birth_date from EMPLOYEES e where e.id=?",
            mapper,
            id
        )
    }

    override fun deleteById(id: Long) {
        jdbc.update("delete from EMPLOYEES where id = ?", id)
    }

    fun count(): Int {
        val count = jdbc.queryForObject("select count(*) from EMPLOYEES", Int::class.java)
        return count ?: 0
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