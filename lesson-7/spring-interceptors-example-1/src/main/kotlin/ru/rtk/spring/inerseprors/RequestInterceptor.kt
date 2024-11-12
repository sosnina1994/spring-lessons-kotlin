package ru.rtk.spring.inerseprors

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

/**
 * Данный компонент нужен для назначения атибутов или заголовков запросов
 */
@Component
class RequestInterceptor : HandlerInterceptor {
    companion object {
        const val START_TIME_ATTRIBUTE_NAME: String = "start_req_time"
        const val REQUEST_ID_HEADER_NAME: String = "x-request-id"
        const val REQUEST_ID_MDC_NAME: String = "request_id"
    }

    private val log = LoggerFactory.getLogger(RequestInterceptor::class.java)


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestId = getRequestId(request)
        MDC.put(REQUEST_ID_MDC_NAME, requestId)
        request.setAttribute(START_TIME_ATTRIBUTE_NAME, System.currentTimeMillis())
        log.info(">>> REQ : ${request.requestURI} with ID $requestId")
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        if (ex != null) {
            log.warn(ex.message, ex)
        }
        log.info("<<<< RESP {}, {}, {}, {}", request.method, request.requestURI, response.status, MDC.get(REQUEST_ID_MDC_NAME))
        MDC.clear()
    }

    private fun getRequestId(request: HttpServletRequest): String {
        var requestId = request.getHeader(REQUEST_ID_HEADER_NAME)

        if (StringUtils.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString()
        }
        return requestId
    }

}