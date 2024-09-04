package ru.rtk.spring.dao

import org.springframework.stereotype.Service
import ru.rtk.spring.domain.Person

@Service
class PersonDaoSimple : PersonDao {
    override fun findByName(name: String): Person {
        return Person(name, 18)

    }
}