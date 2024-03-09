package br.org.acal.apicore.domain.entity

import java.time.LocalDateTime

data class Proposal(
    val reference: Reference,
    val emission: LocalDateTime,
    val link: Link,
    val invoiceDetails: List<InvoiceDetail>,
)