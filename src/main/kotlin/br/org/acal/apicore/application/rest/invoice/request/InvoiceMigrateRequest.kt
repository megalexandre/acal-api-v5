package br.org.acal.apicore.application.rest.invoice.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.common.enums.Reason.CATEGORY
import br.org.acal.apicore.common.enums.Reason.WATER
import br.org.acal.apicore.domain.entity.InvoiceDetail
import br.org.acal.apicore.domain.entity.InvoiceMigrate
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.domain.entity.Reference
import io.azam.ulidj.ULID.random
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.validation.annotation.Validated

@Validated
data class InvoiceMigrateRequest (

    val id: String?,
    val linkId: String,
    val reference: String,
    val invoiceNumber: String ?,
    val emission: LocalDateTime,
    val dueDate: LocalDate,
    val water: BigDecimal,
    val category: BigDecimal?,
    val dataPaid: LocalDateTime?,
): RequestAdapter<InvoiceMigrate> {

    override fun toEntity(): InvoiceMigrate = InvoiceMigrate(
        id = id ?: random(),
        reference = Reference(reference),
        invoiceNumber = invoiceNumber?.let { InvoiceNumber(it) },
        emission = emission,
        dueDate = dueDate,
        linkId = linkId,
        invoiceDetails = listOf(
            InvoiceDetail(reason = WATER, value = water, dataPaid = dataPaid),
            InvoiceDetail(reason = CATEGORY, value = category ?: ZERO, dataPaid = dataPaid)
        ))
}


fun List<InvoiceMigrateRequest>.toEntity(): List<InvoiceMigrate> = map { it.toEntity() }