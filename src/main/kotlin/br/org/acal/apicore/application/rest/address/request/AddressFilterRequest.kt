package br.org.acal.apicore.application.rest.address.request

import br.org.acal.apicore.domain.dto.pagination.address.AddressFilter
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
class AddressFilterRequest(
    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val areaId: String?,
    @RequestParam(required = false) val areaName: String?,
    @RequestParam(required = false) val number: String?,
    @RequestParam(required = false) val letter: String? = null,
    @RequestParam(required = false) val hasHydrometer: Boolean? = null,
    @RequestParam(required = false) val active: Boolean? = null,
    ){
    fun toAddressFilter() = AddressFilter(
        id = this.id,
        areaId = areaId,
        areaName = areaName,
        number = number,
        letter = letter,
        hasHydrometer = hasHydrometer,
        active = active
    )
}