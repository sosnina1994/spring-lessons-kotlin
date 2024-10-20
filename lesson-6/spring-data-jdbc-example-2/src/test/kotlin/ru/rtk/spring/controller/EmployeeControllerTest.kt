package ru.rtk.spring.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import ru.rtk.spring.controller.dto.CreateEmployeeRequest
import ru.rtk.spring.controller.dto.EmployeeResponse
import java.time.LocalDate

@DisplayName("Тест контроллера")
@AutoConfigureMockMvc
@SpringBootTest
internal class EmployeeControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun create_withCreated() {

        val req = getReqExample()
        val resp = getRespExample()

        mvc.post("/employees") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(req)
        }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id").value(resp.id)
                jsonPath("$.fullName").value(resp.fullName)
                jsonPath("$.email").value(resp.email)
                jsonPath("$.phoneNumber").value(resp.phoneNumber)
                jsonPath("$.birthDate").value(resp.birthDate)
            }
    }

    private fun getReqExample(): CreateEmployeeRequest {
        return CreateEmployeeRequest("test", "test@mail.ru", "test", LocalDate.now())
    }

    private fun getRespExample(): EmployeeResponse {
        return EmployeeResponse(1L, "test", "test@mail.ru", "test", LocalDate.now())
    }


}