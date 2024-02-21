package br.org.acal.apicore.resources.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "address")
data class AddressDocument(

    @Id
    val id: String,
    val area: AreaDocument,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
    val active: Boolean,

)