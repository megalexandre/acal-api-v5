package br.org.acal.apicore.resources.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sequence")
data class SequenceDocument(

    @Id
    val id: String,

    val value: Long

)