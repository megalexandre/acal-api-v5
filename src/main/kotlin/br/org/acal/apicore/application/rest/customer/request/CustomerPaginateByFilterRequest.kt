package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.dto.pagination.customer.CustomerPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerPaginateByFilterRequest (

    val filter: CustomerFindByFilterRequest? = null,
    val limitOffsetAndSort: LimitOffsetAndSort? = null,

): RequestAdapter<CustomerPageFilter> {

    override fun toEntity(): CustomerPageFilter = CustomerPageFilter(
        filter = filter?.toEntity(),
        limitOffsetAndSort = limitOffsetAndSort,
    )

}