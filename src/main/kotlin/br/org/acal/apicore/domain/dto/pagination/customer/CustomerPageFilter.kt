package br.org.acal.apicore.domain.dto.pagination.customer

import br.org.acal.apicore.domain.dto.pagination.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.PageFilter
import br.org.acal.apicore.domain.dto.pagination.SortField

class CustomerPageFilter(

    override val filter: CustomerFilter? = null,
    override val limitOffset: LimitOffset? = null,
    override val sortField: SortField? = null,

): PageFilter(limitOffset, sortField)
