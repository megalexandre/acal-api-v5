package br.org.acal.apicore.application.rest.invoice.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.application.rest.components.validator.invoiceNumber.InvoiceNumberValidator
import br.org.acal.apicore.common.enums.Reason
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceDetail
import br.org.acal.apicore.domain.entity.InvoiceMigrate
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.domain.entity.Reference
import io.azam.ulidj.ULID.random
import java.math.BigDecimal
import java.time.LocalDateTime
import org.springframework.validation.annotation.Validated

@Validated
data class InvoiceMigrateRequest (

    val id: String?,
    val linkId: String,
    val reference: String,
    
    val invoiceNumber: String ?,

    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
    val invoiceDetails: List<InvoiceDetailRequest>,

    ): RequestAdapter<InvoiceMigrate> {

    override fun toEntity(): InvoiceMigrate = InvoiceMigrate(
        id = id ?: random(),
        reference = Reference(reference),
        invoiceNumber = invoiceNumber?.let { InvoiceNumber(it) },
        emission = emission,
        dueDate = dueDate,
        linkId = linkId,
        invoiceDetails = invoiceDetails.map { it.toEntity() }
    )

}


fun List<InvoiceMigrateRequest>.toEntity(): List<InvoiceMigrate> = map { it.toEntity() }