package ru.rtk.spring.controller

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.switchIfEmpty
import ru.rtk.spring.domain.CreateEmployeeRequest
import ru.rtk.spring.domain.EmployeeResponse
import ru.rtk.spring.mapper.EmployeeMapper
import ru.rtk.spring.repo.EmployeeRepo

@RestController
class EmployeeController(private val repo: EmployeeRepo, private val mapper: EmployeeMapper) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/employees")
    fun create(@Valid @RequestBody employeeRequest: CreateEmployeeRequest): Mono<ResponseEntity<EmployeeResponse>> {
        return repo.save(mapper.mapToEmployee(employeeRequest))
            .map { ResponseEntity(mapper.mapToResp(it), HttpStatus.CREATED) }
            .onErrorResume {
                log.warn("Исключение", it)
                Mono.just(ResponseEntity.badRequest().build())
            }
            .switchIfEmpty { Mono.fromCallable { ResponseEntity.badRequest().build() } }
    }

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Long): Mono<ResponseEntity<EmployeeResponse>> {
        return repo.findById(id).map { ResponseEntity(mapper.mapToResp(it), HttpStatus.OK) }
            .onErrorResume { Mono.fromCallable { ResponseEntity.badRequest().build() } }
            .switchIfEmpty { Mono.fromCallable { ResponseEntity.notFound().build() } }
    }

    @GetMapping("/employees")
    fun findAll(): Flux<EmployeeResponse> {
        return repo.findAll().publishOn(Schedulers.boundedElastic()).map { mapper.mapToResp(it) }
    }

    @DeleteMapping("/employees/{id}")
    fun deleteById(@PathVariable id: Long): Mono<ResponseEntity<Void>> {
        return repo.deleteById(id)
            .then(Mono.fromCallable { ResponseEntity(HttpStatus.NO_CONTENT) })
    }
}