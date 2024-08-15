package br.org.acal.apicore.application.rest.address.response

import br.org.acal.apicore.domain.entity.Address

class AddressCreateResponse (
    val id: String
){
    constructor(address: Address) : this(
        id = address.id,
    )
}
