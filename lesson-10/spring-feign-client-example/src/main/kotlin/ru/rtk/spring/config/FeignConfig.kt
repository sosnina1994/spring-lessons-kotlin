package ru.rtk.spring.config

import feign.Request
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FeignConfig {
    @Bean
    fun requestOptions(): Request.Options {
        return Request.Options(5000, 1000) // 5 секунд на подключение и 10 секунд на чтение
    }

}