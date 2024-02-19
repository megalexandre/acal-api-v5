package br.org.acal.apicore.resources.document

import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.domain.entity.Reference
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
@Document(collection = "invoice")
data class InvoiceDocument(

    @Id
    val id: String,

    val reference: Reference,
    @Indexed(unique = true)
    val invoiceNumber: InvoiceNumber,
    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
    val linkId: String,

)