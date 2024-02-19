package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType
import java.time.LocalDate

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
