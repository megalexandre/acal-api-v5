package br.org.acal.apicore.resources.document

import br.org.acal.apicore.domain.entity.PhoneNumber
import br.org.acal.apicore.resources.document.interfaces.DocumentItem
import java.time.LocalDate
import java.time.LocalDateTime
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

    override val createdAt: LocalDateTime,

    override val createdBy: String?,

) : DocumentItem
