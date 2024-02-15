package br.org.acal.apicore.application.rest.customer.response

import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.PhoneNumber
import java.time.LocalDate
import java.time.LocalDateTime

data class CustomerGetResponse (
    val id: String,
    val name: String,
    val documentNumber: String,
    var birthDay: LocalDate? = null,
    val preferentialNumber: String? = null,
    val phoneNumbers: List<PhoneNumber>? = null,
    val createdAt: LocalDateTime,
    val active: Boolean,
) {
    constructor(customer: Customer) : this(
        id = customer.id,
        name = customer.name,
        documentNumber = customer.documentNumber.number,
        birthDay = customer.birthDay,
        phoneNumbers = customer.phoneNumbers,
        preferentialNumber = customer.phoneNumbers?.first { it.preferential }?.number,
        createdAt = customer.createdAt,
        active = customer.active,
    )
}



