package ru.rtk.spring.service

import org.springframework.stereotype.Service
import ru.rtk.spring.dao.PersonDao
import ru.rtk.spring.domain.Person

@Service
class PersonServiceImpl(
    private val dao: PersonDao
) : PersonService {

    override fun getByName(name: String): Person {
        return dao.findByName(name)
    }
}