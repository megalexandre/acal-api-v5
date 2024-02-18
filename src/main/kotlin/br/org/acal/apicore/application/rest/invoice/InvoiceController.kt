package br.org.acal.apicore.application.rest.invoice

import br.org.acal.apicore.application.rest.category.response.CategoryFindAllResponse
import br.org.acal.apicore.application.rest.invoice.request.InvoiceCreateRequest
import br.org.acal.apicore.domain.usecases.category.CategoryFindAllUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoiceCreateUsecase
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("invoice", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class InvoiceController(
    private val create: InvoiceCreateUsecase
){
    @PostMapping
    fun create(@Valid @RequestBody request: InvoiceCreateRequest) =
        create.execute(request.toEntity())

}
