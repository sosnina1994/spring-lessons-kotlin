package ru.rtk.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    @Bean
    fun restClient(restClientBuilder: RestClient.Builder): RestClient {
        val factory = HttpComponentsClientHttpRequestFactory()
        factory.setConnectTimeout(10)
        factory.setConnectionRequestTimeout(10)
        factory.setConnectionRequestTimeout(10)

        return RestClient.builder()
            .requestFactory(factory)
            .baseUrl("http://localhost:8085")
            .build()
    }
}