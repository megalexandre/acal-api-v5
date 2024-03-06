package br.org.acal.apicore.application.rest.address.response

import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area

class AddressGetResponse (
    val id: String,
    val area: AreaFindResponse,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
) {
    constructor(address: Address): this(
        id = address.id,
        area = AreaFindResponse(address.area),
        number = address.number,
        letter = address.letter,
        hasHydrometer =  address.hasHydrometer,
    )

}

class AreaFindResponse(
    val id: String,
    val name: String,
){

    constructor(area: Area): this(
        id = area.id,
        name = area.name,
    )
}