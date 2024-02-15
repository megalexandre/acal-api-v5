package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.dto.pagination.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.SortField
import br.org.acal.apicore.domain.dto.pagination.customer.CustomerPageFilter
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerPaginateByFilterRequest (

    val filter: CustomerFindByFilterRequest? = null,
    val limitOffset: LimitOffset? = null,
    val sortField: SortField? = null,

): RequestAdapter<CustomerPageFilter> {

    override fun toEntity(): CustomerPageFilter = CustomerPageFilter(
        filter = filter?.toEntity(),
        limitOffset = limitOffset,
        sortField = sortField,
    )

}