package ru.rtk.spring.logging

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    @Before("@annotation(ru.rtk.spring.logging.Logging)")
    fun logBefore(joinPoint: JoinPoint) {
        println("<<- BEFORE ->>")

        println("Прокси : " + joinPoint.getThis().javaClass.name)
        println("Класс : " + joinPoint.target.javaClass.name)
        println("Вызов метода : " + joinPoint.signature.name)

        println("<<- BEFORE ->>")

    }

//    @After("@annotation(ru.rtk.spring.logging.Logging)")
//    fun logAfter(joinPoint: JoinPoint) {
//        println("<<- AFTER ->>")
//
//        println("Прокси : " + joinPoint.getThis().javaClass.name)
//        println("Класс : " + joinPoint.target.javaClass.name)
//        println("Вызов метода : " + joinPoint.signature.name)
//        println("результат : " + joinPoint.args[0])
//
//        println("<<- AFTER ->>")
//    }

//    @Around("@annotation(ru.rtk.spring.logging.Logging)")
//    fun logAround(joinPoint: ProceedingJoinPoint): Any {
//        println("<<- AROUND ->>")
//
//        println("Прокси : " + joinPoint.getThis().javaClass.name)
//        println("Класс : " + joinPoint.target.javaClass.name)
//        println("Вызов метода : " + joinPoint.signature.name)
//
//        val result = joinPoint.proceed()
//
//        println("<<- AROUND ->>")
//        return result
//    }


}