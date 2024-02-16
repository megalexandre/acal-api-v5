package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType
import br.org.acal.apicore.domain.entity.interfaces.Entity
import java.time.LocalDate
import java.time.LocalDateTime

data class Customer (

    val id: String,
    val legacyId: String?,
    val name: String,
    val documentNumber: DocumentNumber,
    var birthDay: LocalDate? = null,
    val phoneNumbers: List<PhoneNumber>? = null,
    val active: Boolean,


){

    val type: PersonType = documentNumber.type

}
