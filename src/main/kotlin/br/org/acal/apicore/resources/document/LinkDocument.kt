package br.org.acal.apicore.resources.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "link")
data class LinkDocument(

    @Id
    val id: String,

    val customer: CustomerDocument,

    val address: AddressDocument,

    val category: CategoryDocument,

)
