package br.org.acal.apicore.domain.entity

import java.time.LocalDateTime

data class InvoiceMigrate(

    val id: String,
    val reference: Reference,
    val invoiceNumber: InvoiceNumber?,
    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
    val linkId: String,
    val invoiceDetails: List<InvoiceDetail>,
)