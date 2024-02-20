package br.org.acal.apicore.domain.entity

import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Invoice(

    val id: String,
    val reference: Reference,
    val invoiceNumber: InvoiceNumber,
    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
    val linkId: String,
    val invoiceDetails: List<InvoiceDetail>,
) {

    companion object{
        private const val LIMIT_OFF_DAYS_BEFORE_CANCELLATION = 59
    }

    val totalValue: BigDecimal = invoiceDetails.sumOf { it.value }

    val totalAwaitingPayment: BigDecimal = invoiceDetails
        .filterNot { it.isPaid }
        .sumOf { it.value }

    val totalPaidValue: BigDecimal = totalValue
        .minus(totalAwaitingPayment)

    val isPayed: Boolean = invoiceDetails.all { it.isPaid }

    val isOverDue: Boolean = dueDate.isBefore(now()) && !isPayed

    val daysInOverDue: Long = when(isOverDue){
        true -> Duration.between(dueDate, now()).toDays()
        false -> 0
    }

    val cancellationOfRisk: Boolean = daysInOverDue > LIMIT_OFF_DAYS_BEFORE_CANCELLATION
}