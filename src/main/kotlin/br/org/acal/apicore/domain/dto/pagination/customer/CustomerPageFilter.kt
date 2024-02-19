package br.org.acal.apicore.domain.dto.pagination.customer

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.SortField

class CustomerPageFilter(

    override val filter: CustomerFilter? = null,
    override val limitOffset: LimitOffset? = null,
    override val sortField: SortField? = null,
): PageFilter(limitOffset, sortField)
