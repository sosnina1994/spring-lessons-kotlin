package ru.rtk.spring.repo

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.TypedQuery
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.rtk.spring.model.Employee
import java.util.*


@Repository
class JpaEmployeeRepository: EmployeeRepo {

    @PersistenceContext
    lateinit var em: EntityManager

    @Transactional // Для примера. Лучше повесить над методом сервиса
    override fun findById(id: Long): Optional<Employee> {
        val query: TypedQuery<Employee> = em.createQuery(
            "SELECT e FROM Employee e WHERE e.id = :id",
            Employee::class.java
        )
        query.setParameter("id", id)

        return Optional.ofNullable(query.singleResult)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Employee> {
        val query: TypedQuery<Employee> = em.createQuery(
            "SELECT e FROM Employee e", Employee::class.java
        )
        return query.resultList
    }

    @Transactional
    override fun save(employee: Employee): Employee {
        if (employee.id == null) {
            em.persist(employee)
            return employee
        }
        return em.merge(employee)
    }

    @Transactional(readOnly = true)
    override fun deleteById(id: Long) {
        val employee = em.find(Employee::class.java, id)
        if (employee != null) {
            em.remove(employee)
        }
    }
}