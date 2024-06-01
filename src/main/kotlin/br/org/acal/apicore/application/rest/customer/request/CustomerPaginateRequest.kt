package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.domain.dto.pagination.customer.CustomerPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import java.time.LocalDate
import org.springframework.data.domain.Sort
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
class CustomerPaginateRequest(
    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val name: String?,
    @RequestParam(required = false) val documentNumber: String?,
    @RequestParam(required = false) val birthDay: LocalDate? = null,
    @RequestParam(required = false) val active: Boolean? = null,
    @RequestParam(required = false) val offset: Int?,
    @RequestParam(required = false) val size: Int?,
    @RequestParam(required = false) val field: String?,
    @RequestParam(required = false) val direction: Sort.Direction?,
){
    fun toCustomerPageFilter() = CustomerPageFilter(
        filter = CustomerFilterRequest(
            id = id,
            name = name,
            documentNumber = documentNumber,
            active = active,
            birthDay = birthDay,
        ).toCustomerFilter(),
        limitOffsetAndSort = LimitOffsetAndSort(
            offset = offset,
            size = size,
            sortField = SortField(field = field, direction = direction),
        ),
    )
}
