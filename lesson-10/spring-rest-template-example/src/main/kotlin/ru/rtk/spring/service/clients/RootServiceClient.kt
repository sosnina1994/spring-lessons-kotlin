package ru.rtk.spring.service.clients

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.rtk.spring.domain.Person

/**
* Клиента-серивс для другого приложения
 *
 * Рекомендуется использовтать интерфейс
 */
@Service
class RootServiceClient(private val rootServiceRestTemplate: RestTemplate) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    companion object {
        private const val BASE_URL = "http://localhost:8085"
    }

    fun get(): Person? {
        try {
            return rootServiceRestTemplate.getForObject("$BASE_URL/", Person::class.java)
        } catch (e: Exception) {
            logger.warn("Root service ex", e)
            return null
        }

    }
}