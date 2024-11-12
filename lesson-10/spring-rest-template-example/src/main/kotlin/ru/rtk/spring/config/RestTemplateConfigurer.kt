package ru.rtk.spring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RestTemplateConfigurer {
    @Bean
    @ConfigurationProperties(prefix = "root-service.rest-config")
    fun restTemplate(): RestTemplateProperties = RestTemplateProperties()
}