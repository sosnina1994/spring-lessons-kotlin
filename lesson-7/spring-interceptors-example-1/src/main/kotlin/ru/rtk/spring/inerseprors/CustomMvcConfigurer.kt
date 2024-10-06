package ru.rtk.spring.inerseprors

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Да, мы немного залезли в наш WebMvcConfigurer
 */
@Component
class CustomMvcConfigurer(private val requestInterceptor: RequestInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor).order(1).excludePathPatterns(
            "/metrics",
            "/swagger-ui/",
            "/v3/",
            "/health"
        )
    }
}