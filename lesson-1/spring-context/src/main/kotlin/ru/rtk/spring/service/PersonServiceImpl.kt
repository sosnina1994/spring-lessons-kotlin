package ru.rtk.spring.service

import ru.rtk.spring.dao.PersonDao
import ru.rtk.spring.domain.Person

class PersonServiceImpl(
    private val dao: PersonDao
) : PersonService {

    override fun getByName(name: String): Person {
        return dao.findByName(name)
    }
}