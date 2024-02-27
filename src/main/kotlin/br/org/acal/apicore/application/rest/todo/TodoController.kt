package br.org.acal.apicore.application.rest.todo

import br.org.acal.apicore.domain.usecases.address.AddressCreateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("todo", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class TodoController(
    private val create: AddressCreateUsecase,
): Sl4jLogger() {

    @GetMapping
    fun todo(): ResponseEntity<Todo> = ok(
        Todo()
    )

}

class Todo(
    val test: String = "meu teste"
)


