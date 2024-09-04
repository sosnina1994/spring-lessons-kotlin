package ru.rtk.spring

import org.springframework.context.support.ClassPathXmlApplicationContext
import ru.rtk.spring.dao.PersonDaoSimple
import ru.rtk.spring.service.PersonService

class SpringContextApplication

fun main(args: Array<String>) {
	val context =
		ClassPathXmlApplicationContext("/spring-context.xml")

	val service1 = context.getBean(PersonService::class.java)
	val service2 = context.getBean(PersonService::class.java)


	// в контексе бин PersonService - singleton
	println("PersonService with scope singleton " + (service1 == service2))

	val dao1 = context.getBean(PersonDaoSimple::class.java)
	val dao2 = context.getBean(PersonDaoSimple::class.java)


	// в контексе бин PersonDaoSimple - prototype
	println("PersonDaoSimple with scope prototype " + (dao1 != dao2))


	//Выполним запрос
	val ivan = service2.getByName("Ivan")
	println("name: " + ivan.name + " age: " + ivan.age)

	context.close()
}
