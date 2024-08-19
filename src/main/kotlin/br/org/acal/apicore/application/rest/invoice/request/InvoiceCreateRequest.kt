package br.org.acal.apicore.application.rest.invoice.request

import br.org.acal.apicore.application.components.validator.invoiceNumber.InvoiceNumberValidator
import br.org.acal.apicore.application.components.validator.notemptylist.NotEmptyList
import br.org.acal.apicore.common.enums.Reason
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceDetail
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.domain.entity.Reference
import io.azam.ulidj.ULID.random
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.validation.annotation.Validated

@Validated
data class InvoiceCreateRequest (

    val linkId: String,
    val reference: String,

    @InvoiceNumberValidator
    val invoiceNumber: String,

    val emission: LocalDateTime?,
    val dueDate: LocalDate,

    @NotEmptyList(message = "Can't create a invoice without details")
    val invoiceDetails: List<InvoiceDetailRequest>,
    ) {

    fun toEntity(): Invoice = Invoice(
        id = random(),
        reference = Reference.of(reference),
        invoiceNumber = InvoiceNumber.of(invoiceNumber),
        emission = emission,
        dueDate = dueDate,
        linkId = linkId,
        invoiceDetails = invoiceDetails.map { it.toEntity() }
    )

}

data class InvoiceDetailRequest(

    val reason: Reason,
    val value: BigDecimal,
    val dataPaid: LocalDateTime?,

) {

    fun toEntity(): InvoiceDetail = InvoiceDetail(
        reason = reason,
        value = value,
        dataPaid = dataPaid,
    )

}

fun List<InvoiceCreateRequest>.toEntity(): List<Invoice> = map { it.toEntity() }