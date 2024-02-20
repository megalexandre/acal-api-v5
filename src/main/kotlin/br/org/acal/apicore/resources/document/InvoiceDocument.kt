package br.org.acal.apicore.resources.document

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "invoice")
data class InvoiceDocument(

    @Id
    val id: String,
    @Indexed(unique = true)
    val reference: String,
    val invoiceNumber: String,
    val emission: LocalDateTime,
    val dueDate: LocalDateTime,
    val linkId: String,

)
