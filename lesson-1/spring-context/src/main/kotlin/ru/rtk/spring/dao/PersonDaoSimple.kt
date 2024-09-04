package ru.rtk.spring.dao

import ru.rtk.spring.domain.Person

class PersonDaoSimple : PersonDao {
    override fun findByName(name: String): Person {
        return Person(name, 18)

    }
}