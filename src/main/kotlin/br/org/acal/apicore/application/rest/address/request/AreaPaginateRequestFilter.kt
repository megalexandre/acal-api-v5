package br.org.acal.apicore.application.rest.address.request

import br.org.acal.apicore.domain.dto.pagination.address.AddressFilter
import br.org.acal.apicore.domain.dto.pagination.address.AddressPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import org.springframework.data.domain.Sort
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
class AreaPaginateRequestFilter(
    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val area: String?,
    @RequestParam(required = false) val number: String?,
    @RequestParam(required = false) val letter: String? = null,
    @RequestParam(required = false) val hasHydrometer: Boolean? = null,
    @RequestParam(required = false) val active: Boolean? = null,
    @RequestParam(required = false) val offset: Int?,
    @RequestParam(required = false) val size: Int?,
    @RequestParam(required = false) val field: String?,
    @RequestParam(required = false) val direction: Sort.Direction?,
){

    fun toAreaPaginateRequest(): AddressPageFilter =
        AddressPageFilter(
            filter = AddressFilter(
                id = id,
                area = area,
                number = number,
                letter = letter,
                hasHydrometer = hasHydrometer,
                active = active
            ),
            limitOffsetAndSort = LimitOffsetAndSort(
                offset = offset,
                size = size,
                sortField = SortField(field = field, direction = direction),
            )
        )

}