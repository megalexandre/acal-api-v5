package br.org.acal.apicore.resources.document

import br.org.acal.apicore.domain.entity.PhoneNumber
import java.time.LocalDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "customer")
data class CustomerDocument(

    @Id
    val id: String,

    val name: String,

    val documentNumber: String,

    var birthDay: LocalDate?,

    val phoneNumbers: List<PhoneNumber>?,

    val active: Boolean,

)