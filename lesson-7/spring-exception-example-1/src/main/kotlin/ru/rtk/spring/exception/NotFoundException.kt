package ru.rtk.spring.exception

class NotFoundException(override val message: String): RuntimeException() {
}