package ru.rtk.spring.repo

import org.springframework.data.jpa.repository.JpaRepository
import ru.rtk.spring.model.User
import java.util.*

interface UserRepo: JpaRepository<User, Long> {
     fun findByUsername(name: String): Optional<User>
}