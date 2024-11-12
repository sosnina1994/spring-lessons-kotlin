package ru.rtk.spring.config

class RestTemplateProperties (
    var connectTimeout: Long = 0,
    var requestTimeout: Long = 0,
    var socketTimeout: Long = 0,
    var maxTotal: Int = 0,
    var defaultMaxPerRoute: Int = 0
)