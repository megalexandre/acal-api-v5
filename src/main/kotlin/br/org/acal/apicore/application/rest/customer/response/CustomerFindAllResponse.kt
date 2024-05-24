package br.org.acal.apicore.application.rest.customer.response

import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.PhoneNumber

data class CustomerFindAllResponse (
    val id: String,
    val name: String,
    val documentNumber: String,
    val phoneNumbers: List<PhoneNumber>? = null,
    val active: Boolean,
) {
    constructor(customer: Customer) : this(
        id = customer.id,
        name = customer.name,
        documentNumber = customer.documentNumber.number,
        phoneNumbers = customer.phoneNumbers,
        active = customer.active,
    )
}



