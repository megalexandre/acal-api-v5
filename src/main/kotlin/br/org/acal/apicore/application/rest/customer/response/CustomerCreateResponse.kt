package br.org.acal.apicore.application.rest.customer.response

import br.org.acal.apicore.domain.entity.Customer

data class CustomerCreateResponse (
    val id: String,
) {
    constructor(customer: Customer) : this(
        id = customer.id,
    )
}



