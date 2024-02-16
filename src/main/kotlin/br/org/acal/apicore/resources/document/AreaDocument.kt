package br.org.acal.apicore.resources.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "area")
class AreaDocument(

    @Id
    val id: String,

    val name: String,
)