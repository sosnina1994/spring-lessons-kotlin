package ru.rtk.spring.service.clients

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import ru.rtk.spring.domain.Person

@Service
class RootClientService(val customRestClient: RestClient) {

    fun getPerson(): Person? {
        return customRestClient.get().uri("/").retrieve().body<Person>()
    }
}