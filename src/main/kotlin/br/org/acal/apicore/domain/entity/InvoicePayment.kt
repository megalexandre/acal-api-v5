package br.org.acal.apicore.domain.entity

import java.math.BigDecimal

class InvoicePayment(
    val invoiceId: String,
    val value: BigDecimal,
)