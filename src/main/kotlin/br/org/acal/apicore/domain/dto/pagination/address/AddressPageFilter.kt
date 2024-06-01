package br.org.acal.apicore.domain.dto.pagination.address

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter

class AddressPageFilter(

    override val filter: AddressFilter? = null,
    override val limitOffsetAndSort: LimitOffsetAndSort? = null,

): PageFilter(limitOffsetAndSort)
