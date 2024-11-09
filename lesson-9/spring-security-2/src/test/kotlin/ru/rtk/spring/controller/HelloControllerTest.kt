package ru.rtk.spring.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@DisplayName("Тестирование контроллера")
@SpringBootTest
@AutoConfigureMockMvc
internal class HelloControllerTest @Autowired constructor(
    private val mvc: MockMvc
) {

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun shouldReturnHelloMessage() {
        mvc.perform(MockMvcRequestBuilders.get("/api/admin"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Hello ADMIN!"))
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun shouldReturnForbidden() {
        mvc.perform(MockMvcRequestBuilders.get("/api/admin"))
            .andExpect(MockMvcResultMatchers.status().isForbidden)
    }


    @Test
    fun shouldReturnUnauthorized () {
        mvc.perform(MockMvcRequestBuilders.get("/api/admin"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
    }

}