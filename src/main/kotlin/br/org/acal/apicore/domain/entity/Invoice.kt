package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.util.LocalDateUtil.Companion.now
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.DAYS

data class Invoice(
    val id: String,
    val reference: Reference,
    val invoiceNumber: InvoiceNumber,
    val emission: LocalDateTime,
    val dueDate: LocalDate,
    val linkId: String,
    val invoiceDetails: List<InvoiceDetail>,
) {

    companion object{
        private const val LIMIT_OFF_DAYS_BEFORE_CANCELLATION = 59
    }

    init {
        require(invoiceDetails.isNotEmpty()){
            "invoice without details is not valid"
        }
    }

    val total: BigDecimal = invoiceDetails.sumOf { it.value }

    val awaitingPaymentValue: BigDecimal = invoiceDetails
        .filterNot { it.isPaid }
        .sumOf { it.value }

    val paidValue: BigDecimal = total
        .minus(awaitingPaymentValue)

    val isPayed: Boolean = invoiceDetails.all { it.isPaid }

    val isNotPayed: Boolean = !isPayed

    val isOverDue: Boolean = dueDate.isBefore(now()) && isNotPayed

    val daysInOverDue: Long = when(isOverDue){
        true -> DAYS.between(dueDate, now())
        false -> 0
    }

    val cancellationOfRisk: Boolean = daysInOverDue > LIMIT_OFF_DAYS_BEFORE_CANCELLATION
}