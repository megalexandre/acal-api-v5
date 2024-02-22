package br.org.acal.apicore.application.rest.invoice

import br.org.acal.apicore.application.rest.invoice.request.InvoiceCreateRequest
import br.org.acal.apicore.application.rest.invoice.request.InvoiceMigrateRequest
import br.org.acal.apicore.application.rest.invoice.request.toEntity
import br.org.acal.apicore.application.rest.invoice.response.InvoiceCreateResponse
import br.org.acal.apicore.domain.usecases.invoice.InvoiceCreateLotUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoiceCreateUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoiceMigrateUsecase
import jakarta.validation.Valid
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("invoice", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class InvoiceController(
    private val create: InvoiceCreateUsecase,
    private val createLot: InvoiceCreateLotUsecase,
    private val migrate: InvoiceMigrateUsecase,
){
    @PostMapping
    fun create(@Valid @RequestBody request: InvoiceCreateRequest): InvoiceCreateResponse =
        InvoiceCreateResponse(create.execute(request.toEntity()))

    @PostMapping("lot")
    fun createLot(@Valid @RequestBody request: List<InvoiceCreateRequest>) {
        createLot.execute(request.toEntity())
    }

    @PostMapping("migrate")
    fun migrate(@Valid @RequestBody request: List<InvoiceMigrateRequest>) {
        migrate.execute(request.toEntity())
    }

}
