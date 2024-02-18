package br.org.acal.apicore.domain.entity

import java.time.LocalDateTime

data class Invoice(

    val id: String,
    val reference: Reference,
    val invoiceNumber: InvoiceNumber,
    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
    val linkId: String,


    // val linkDetail: LinkDetail,
   // val invoiceDetails: List<InvoiceDetail>,
) {
    /*
    val totalValue: BigDecimal = invoiceDetails
        .sumOf { it.value }

    val totalAwaitingPayment: BigDecimal = invoiceDetails
        .filter { it.isNotPaid }
        .sumOf { it.value }

    val totalPaidValue: BigDecimal = totalValue
        .minus(totalAwaitingPayment)

    val isPayed: Boolean = !invoiceDetails
        .any { it.isNotPaid }

    val isOverDue: Boolean = dueDate
        .isBefore(now()) && !isPayed

    val daysInOverDue: Long = when(isOverDue){
        true -> Duration.between(dueDate, now()).toDays()
        false -> 0
    }

    val cancellationOfRisk: Boolean = daysInOverDue > 59
     */
}
