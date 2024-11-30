package ru.rtk.spring.service

import org.springframework.stereotype.Service
import ru.rtk.spring.service.clients.RootClientService

@Service
class OurService(private val rootServiceClient: RootClientService){

    fun getUser() = rootServiceClient.getPerson()
}