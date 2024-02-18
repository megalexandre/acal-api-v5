package br.org.acal.apicore.application.rest.invoice.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.domain.entity.Reference
import io.azam.ulidj.ULID.random
import java.time.LocalDateTime
import org.springframework.validation.annotation.Validated

@Validated
data class InvoiceCreateRequest (

    val id: String?,
    val linkId: String,
    val reference: String,
    val invoiceNumber: String,
    val emission: LocalDateTime,
    val dueDate: LocalDateTime,

): RequestAdapter<Invoice> {

    override fun toEntity(): Invoice = Invoice(
        id = id ?: random(),
        reference = Reference(reference),
        invoiceNumber = InvoiceNumber(invoiceNumber),
        emission = emission,
        dueDate = dueDate,
        linkId = linkId,
    )

}

fun List<InvoiceCreateRequest>.toEntity(): List<Invoice> = map { it.toEntity() }