package ru.rtk.spring.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.rtk.spring.domain.ApiException
import ru.rtk.spring.exception.NotFoundException


// или @ControllerAdvice + @ResponseBody
@RestControllerAdvice
class RestControllerAdvice {

    /** Обработка ошибок валидации NotNull&NotBlank*/
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleEntityValidateEx(ex: MethodArgumentNotValidException): ApiException {
        val details = ex.bindingResult.fieldErrors.map { "${it.field} = ${it.rejectedValue} : ${it.defaultMessage}" }
        return ApiException(details, HttpStatus.BAD_REQUEST)
    }

    /** Обработка ошибок кастомных NotFoundException*/
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleEntityValidateEx(ex: NotFoundException): ApiException {
        val details = listOf(ex.message)
        return ApiException(details, HttpStatus.BAD_REQUEST)
    }
}
