package ru.rtk.spring.service

import ru.rtk.spring.domain.Person

interface PersonService {
    fun getByName(name: String): Person

}