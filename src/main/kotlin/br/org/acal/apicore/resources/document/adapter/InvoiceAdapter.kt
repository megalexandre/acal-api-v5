package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.resources.document.InvoiceDocument
import org.springframework.data.domain.Page

fun Invoice.toDocument() = InvoiceDocument(
    id = id,
    invoiceNumber = invoiceNumber.value,
    reference = invoiceNumber.reference.value,
    emission = emission,
    dueDate = dueDate,
    linkId = linkId,
)

fun InvoiceDocument.toEntity() = Invoice(
    id = id,
    reference = InvoiceNumber(invoiceNumber).reference,
    invoiceNumber = InvoiceNumber(invoiceNumber),
    emission = emission,
    dueDate = dueDate,
    linkId = linkId,
)

fun Page<InvoiceDocument>.toEntity() = map { it.toEntity()  }