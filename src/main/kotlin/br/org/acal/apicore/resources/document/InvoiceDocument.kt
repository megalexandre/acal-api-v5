package br.org.acal.apicore.resources.document

import br.org.acal.apicore.common.enums.Reason
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

data class InvoiceDetailDocument(
    val reason: Reason,
    val value: BigDecimal,
    val dataPaid: LocalDateTime?,
)

@Document(collection = "invoice")
data class InvoiceDocument(

    @Id
    val id: String,
    @Indexed(unique = true)
    val reference: String,
    val invoiceNumber: String,
    val emission: LocalDateTime,
    val dueDate: LocalDate,
    val linkId: String,
    val invoiceDetails: List<InvoiceDetailDocument>
)
