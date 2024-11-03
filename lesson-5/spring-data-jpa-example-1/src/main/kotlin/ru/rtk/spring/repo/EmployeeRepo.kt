package ru.rtk.spring.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.rtk.spring.model.Employee
import java.util.*


interface EmployeeRepo: JpaRepository<Employee, Long> {
    fun findEmployeeByFullNameContainsIgnoreCase(name: String): Optional<Employee>

    @Query("select e from Employee e where lower(e.fullName) like concat('%', :name,'%')")
    fun findByName(@Param("name") name: String): Optional<Employee>

}