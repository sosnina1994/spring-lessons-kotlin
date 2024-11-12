package ru.rtk.spring.service

import org.springframework.stereotype.Service
import ru.rtk.spring.service.clients.RootServiceClient

@Service
class OurService(private val rootServiceClient: RootServiceClient){

    fun getUser() = rootServiceClient.get()
}