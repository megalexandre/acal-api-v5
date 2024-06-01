package br.org.acal.apicore.application.rest.address.response

import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area

data class AddressFindAllResponse (
    val id: String,
    val area: Area,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
    val active: Boolean,
) {
    constructor(address: Address) : this(
        id = address.id,
        area = address.area,
        number = address.number,
        letter = address.letter,
        hasHydrometer = address.hasHydrometer,
        active = address.active
    )
}



