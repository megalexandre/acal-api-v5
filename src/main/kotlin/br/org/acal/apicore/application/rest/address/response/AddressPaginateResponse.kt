package br.org.acal.apicore.application.rest.address.response

import br.org.acal.apicore.domain.entity.Address

data class AddressPaginateResponse (
    val id: String,
    val area: String,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
    val active: Boolean,
) {
    constructor(address: Address) : this(
        id = address.id,
        area = address.area.name,
        number = address.number,
        letter = address.letter,
        hasHydrometer = address.hasHydrometer,
        active = address.active
    )
}