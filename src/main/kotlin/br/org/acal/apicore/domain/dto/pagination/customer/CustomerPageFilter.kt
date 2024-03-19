package br.org.acal.apicore.domain.dto.pagination.customer

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.SortField

class CustomerPageFilter(

    override val filter: CustomerFilter? = null,
    override val limitOffsetAndSort: LimitOffsetAndSort? = null,
    override val sortField: SortField? = null,
): PageFilter(limitOffsetAndSort, sortField)
