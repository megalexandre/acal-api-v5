package br.org.acal.apicore.application.rest.invoice

import br.org.acal.apicore.application.rest.components.validator.reference.ReferenceValid
import br.org.acal.apicore.application.rest.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.application.rest.invoice.request.InvoiceCreateRequest
import br.org.acal.apicore.application.rest.invoice.request.InvoiceMigrateRequest
import br.org.acal.apicore.application.rest.invoice.request.toEntity
import br.org.acal.apicore.application.rest.invoice.response.InvoiceCreateResponse
import br.org.acal.apicore.application.rest.invoice.response.InvoiceGetResponse
import br.org.acal.apicore.application.rest.invoice.response.ProposalGroupResponse
import br.org.acal.apicore.domain.entity.Reference
import br.org.acal.apicore.domain.usecases.invoice.InvoiceCreateLotUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoiceCreateUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoiceGetUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoiceMigrateUsecase
import br.org.acal.apicore.domain.usecases.invoice.InvoicePayUsecase
import br.org.acal.apicore.domain.usecases.link.LinkGetUsecase
import br.org.acal.apicore.domain.usecases.proposal.CreateProposalUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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
    private val pay: InvoicePayUsecase,
    private val get: InvoiceGetUsecase,
    private val linkGet: LinkGetUsecase,
    private val proposal: CreateProposalUsecase,
): Sl4jLogger() {

    @GetMapping("proposal/{reference}")
    fun proposal(@Valid @PathVariable @ReferenceValid reference: String): ResponseEntity<List<ProposalGroupResponse>> {
        logger.info { "starting getting proposal by reference: $reference" }
        return ok(ProposalGroupResponse.of(proposal.execute(Reference.of(reference))).also {
            logger.info { "returning proposal" }
        })
    }

    @GetMapping("/{id}")
    fun getById(@Valid @PathVariable @ULIDValidator id: String): ResponseEntity<InvoiceGetResponse> {
        logger.info { "starting getting invoice by: $id" }

        val invoice = get.execute(id)
        val link = linkGet.execute(invoice.linkId)

        return ok(InvoiceGetResponse.of(
            invoice = invoice,
            link = link
        )).also {
            logger.info { "returning invoice from id: $id" }
        }

    }

    @PatchMapping("pay/{id}")
    fun pay(@Valid @PathVariable @ULIDValidator id: String) = run{
        logger.info { "starting payment from invoice: $id" }
        ok(pay.execute(id))
        .also {
            logger.info { "finish payment from invoice: $id" }
        }

    }

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
