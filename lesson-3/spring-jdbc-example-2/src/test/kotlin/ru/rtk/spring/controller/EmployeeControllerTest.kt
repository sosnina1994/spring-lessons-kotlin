package ru.rtk.spring.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.service.EmployeeService
import java.time.LocalDate

@DisplayName("Тест контроллера")
@WebMvcTest(EmployeeController::class)
internal class EmployeeControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    private val mapper: ObjectMapper,
) {

    @MockkBean
    lateinit var employeeService: EmployeeService

    @Test
    fun create_withCreated() {

        val req = getReqExample()
        val resp = getRespExample()

        every { employeeService.save(any()) } returns resp

        mvc.post("/employees") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(req)
        }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }

        verify(exactly = 1) { employeeService.save(withArg { req === it }) }
    }

    private fun getReqExample(): CreateEmployeeRequest {
        return CreateEmployeeRequest("test", "test@mail.ru", "test", LocalDate.now())
    }

    private fun getRespExample(): EmployeeResponse {
        return EmployeeResponse(1L, "test", "test@mail.ru", "test", LocalDate.now())
    }


}