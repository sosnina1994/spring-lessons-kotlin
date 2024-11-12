package ru.rtk.spring.config

import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.core5.util.Timeout
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class RestTemplateConfig {

    @Bean(name = ["rootServiceRestTemplate"])
    fun rootRestTemplate(
        builder: RestTemplateBuilder,
        props: RestTemplateProperties
    ): RestTemplate {
        return getRestTemplate(
            props = props
        )
    }

     fun getRestTemplate(
        props: RestTemplateProperties,
        connectionManager: PoolingHttpClientConnectionManager = PoolingHttpClientConnectionManager()
    ): RestTemplate {

        connectionManager.maxTotal = props.maxTotal
        connectionManager.defaultMaxPerRoute = props.defaultMaxPerRoute

         val requestConfig = RequestConfig.custom()
             .setConnectionRequestTimeout(Timeout.ofSeconds(props.requestTimeout.toLong()))
             .setResponseTimeout(Timeout.ofSeconds(props.socketTimeout.toLong()))
             .setConnectTimeout(Timeout.ofSeconds(props.connectTimeout.toLong()))
             .build()




         val httpClient = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .build()


        val requestFactory = HttpComponentsClientHttpRequestFactory(httpClient)
        return RestTemplate(requestFactory)
    }
}