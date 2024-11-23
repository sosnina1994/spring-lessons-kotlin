package ru.rtk.spring.service

import org.springframework.stereotype.Service
import ru.rtk.spring.service.clients.RootServiceFeignClient

@Service
class OurService(private val rootServiceClient: RootServiceFeignClient){

    fun getUser() = rootServiceClient.getPerson()
}