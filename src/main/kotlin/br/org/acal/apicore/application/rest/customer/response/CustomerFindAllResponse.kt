package br.org.acal.apicore.application.rest.customer.response

import br.org.acal.apicore.domain.entity.Customer

data class CustomerFindAllResponse (
    val id: String,
    val name: String,
    val documentNumber: String,
    val preferentialNumber: String? = null,
) {
    constructor(customer: Customer) : this(
        id = customer.id,
        name = customer.name,
        documentNumber = customer.documentNumber.number,
        preferentialNumber = customer.phoneNumbers?.first { it.preferential }?.number,
    )
}



