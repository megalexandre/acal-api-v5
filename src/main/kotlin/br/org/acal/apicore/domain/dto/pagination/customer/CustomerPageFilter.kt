package br.org.acal.apicore.domain.dto.pagination.customer

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter

class CustomerPageFilter(

    override val filter: CustomerFilter? = null,
    override val limitOffsetAndSort: LimitOffsetAndSort? = null,

): PageFilter(limitOffsetAndSort)
