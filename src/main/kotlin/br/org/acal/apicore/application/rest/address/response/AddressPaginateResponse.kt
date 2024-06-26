package br.org.acal.apicore.application.rest.address.response

import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area

data class AddressPaginateResponse (
    val id: String,
    val area: AreaResponse,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
    val active: Boolean,
) {
    constructor(address: Address) : this(
        id = address.id,
        area = AreaResponse.of(area = address.area),
        number = address.number,
        letter = address.letter,
        hasHydrometer = address.hasHydrometer,
        active = address.active
    )
}

data class AreaResponse(
    val id: String,
    val name: String,
){
    companion object{
       fun of(area: Area): AreaResponse = AreaResponse(area.id, area.name)
    }

}