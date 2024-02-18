package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.resources.document.InvoiceDocument
import java.time.LocalDateTime
import org.springframework.data.domain.Page

fun Invoice.toDocument() = InvoiceDocument(
    id = id,
    reference = reference,
    invoiceNumber = invoiceNumber,
    emission = emission,
    dueDate = dueDate,
    linkId = linkId,
)

fun InvoiceDocument.toEntity() = Invoice(
    id = id,
    reference = reference,
    invoiceNumber = invoiceNumber,
    emission = emission,
    dueDate = dueDate,
    linkId = linkId,
)

fun Page<InvoiceDocument>.toEntity() = map { it.toEntity()  }