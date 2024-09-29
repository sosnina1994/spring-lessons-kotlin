package ru.rtk.spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.rtk.spring.model.Employee
import ru.rtk.spring.repo.EmployeeRepo
import java.time.LocalDate

@SpringBootApplication
class SpringDataJpa1Application

fun main() {

	val context: org.springframework.context.ConfigurableApplicationContext =
		SpringApplication.run(SpringDataJpa1Application::class.java)

	val repository = context.getBean(EmployeeRepo::class.java)

	repository.save(Employee(null,"Александр Александров", birthDate = LocalDate.now()))
	repository.save(Employee(null,"Иван Иванов", birthDate = LocalDate.now()))

	println(repository.count())

	repository.findById(1)

	println(repository.findById(1))

	val findByName = repository.findEmployeeByFullNameContainsIgnoreCase("ив")
	println(findByName)

	val findByNameByQuery = repository.findByName("ив")
	println(findByNameByQuery)


}
