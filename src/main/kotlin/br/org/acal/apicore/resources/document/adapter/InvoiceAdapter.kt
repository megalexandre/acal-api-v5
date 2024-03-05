package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceDetail
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.resources.document.InvoiceDetailDocument
import br.org.acal.apicore.resources.document.InvoiceDocument
import org.springframework.data.domain.Page

fun Invoice.toDocument() = InvoiceDocument(
    id = id,
    invoiceNumber = invoiceNumber.value,
    reference = invoiceNumber.reference.value,
    emission = emission,
    dueDate = dueDate,
    linkId = linkId,
    invoiceDetails = invoiceDetails.toDocument()
)

fun InvoiceDocument.toEntity() = Invoice(
    id = id,
    reference = InvoiceNumber.of(invoiceNumber).reference,
    invoiceNumber = InvoiceNumber.of(invoiceNumber),
    emission = emission,
    dueDate = dueDate,
    linkId = linkId,
    invoiceDetails = invoiceDetails.toEntity()
)


fun InvoiceDetail.toDocument(): InvoiceDetailDocument = InvoiceDetailDocument(
     reason = reason,
     value = value,
     dataPaid= dataPaid,
)

fun InvoiceDetailDocument.toEntity(): InvoiceDetail = InvoiceDetail(
    reason = reason,
    value = value,
    dataPaid= dataPaid,
)

fun List<InvoiceDetail>.toDocument(): List<InvoiceDetailDocument> = map { it.toDocument() }
fun List<InvoiceDetailDocument>.toEntity(): List<InvoiceDetail> = map { it.toEntity() }


fun Page<InvoiceDocument>.toEntity() = map { it.toEntity()  }