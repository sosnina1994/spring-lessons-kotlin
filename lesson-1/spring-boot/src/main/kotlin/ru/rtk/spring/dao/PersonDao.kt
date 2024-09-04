package ru.rtk.spring.dao

import ru.rtk.spring.domain.Person

interface PersonDao {
    fun findByName(name: String): Person
}