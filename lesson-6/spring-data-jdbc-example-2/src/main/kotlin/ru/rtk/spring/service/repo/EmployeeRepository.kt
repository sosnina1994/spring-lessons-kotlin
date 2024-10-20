package ru.rtk.spring.service.repo

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.rtk.spring.model.Employee
import java.math.BigDecimal

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long> {

    @Query(
        """      
        SELECT DISTINCT
          emp.ID,
          emp.FULL_NAME,
          emp.BIRTH_DATE,
          contact.ID AS CONTACT_ID,
          contact.EMAIL AS contact_EMAIL,
          contact.PHONE_NUMBER AS contact_PHONE_NUMBER
          FROM EMPLOYEES as emp
           LEFT JOIN CONTACTS contact ON contact.EMPLOYEE_ID = emp.ID
           LEFT JOIN EMPLOYEE_HISTORIES history ON emp.id = history.employee_id
            where DAY_COST >= :dayCost
    """
    )
    fun findAllByHistoriesDayCost(dayCost: BigDecimal): List<Employee>
}