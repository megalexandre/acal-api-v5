package br.org.acal.apicore.application.rest.customer.response

import br.org.acal.apicore.domain.entity.Customer

data class CustomerPaginateResponse (
    val id: String,
    val name: String,
    val documentNumber: String,
) {
    constructor(customer: Customer) : this(
        id = customer.id,
        name = customer.name,
        documentNumber = customer.documentNumber.number,
    )
}



