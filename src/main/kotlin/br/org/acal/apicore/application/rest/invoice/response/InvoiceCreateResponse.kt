package br.org.acal.apicore.application.rest.invoice.response

import br.org.acal.apicore.domain.entity.Invoice
import java.time.LocalDate
import java.time.LocalDateTime

data class InvoiceCreateResponse(
    val id: String,
    val reference: String,
    val invoiceNumber: String,
    val emission: LocalDateTime,
    val dueDate: LocalDate,
    val linkId: String,
) {

    constructor(invoice: Invoice) : this(
        id = invoice.id,
        reference = invoice.reference.toString(),
        invoiceNumber = invoice.invoiceNumber.value,
        emission = invoice.emission,
        dueDate = invoice.dueDate,
        linkId = invoice.linkId,
    )

}
