package ru.rtk.spring.service.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import ru.rtk.spring.config.FeignConfig
import ru.rtk.spring.domain.Person


@FeignClient(name = "root-service", url = "http://localhost:8085", configuration = [FeignConfig::class])
interface RootServiceFeignClient {

    @GetMapping("/")
    fun getPerson(): Person

}