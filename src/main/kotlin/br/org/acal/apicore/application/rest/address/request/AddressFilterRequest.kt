package br.org.acal.apicore.application.rest.address.request

import br.org.acal.apicore.domain.dto.pagination.address.AddressFilter
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
class AddressFilterRequest(
    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val area: String?,
    @RequestParam(required = false) val number: String?,
    @RequestParam(required = false) val letter: String? = null,
    @RequestParam(required = false) val hasHydrometer: Boolean? = null,
    @RequestParam(required = false) val active: Boolean? = null,
    ){
    fun toAddressFilter() = AddressFilter(
        id = this.id,
        area = area,
        number = number,
        letter = letter,
        hasHydrometer = hasHydrometer,
        active = active
    )
}