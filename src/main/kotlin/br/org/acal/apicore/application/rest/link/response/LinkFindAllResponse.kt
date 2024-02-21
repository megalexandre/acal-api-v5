package br.org.acal.apicore.application.rest.link.response

import br.org.acal.apicore.application.rest.address.response.AddressFindResponse
import br.org.acal.apicore.application.rest.category.response.CategoryFindResponse
import br.org.acal.apicore.application.rest.customer.response.CustomerFindAllResponse
import br.org.acal.apicore.domain.entity.Link

data class LinkFindAllResponse (

    val id: String,
    val customer: CustomerFindAllResponse,
    val address: AddressFindResponse,
    val category: CategoryFindResponse,

    ){
    constructor(link: Link) : this(
        id = link.id,
        customer = CustomerFindAllResponse(link.customer),
        address = AddressFindResponse(link.address),
        category = CategoryFindResponse(link.category),
    )
}