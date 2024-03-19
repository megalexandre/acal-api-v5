package br.org.acal.apicore.domain.dto.pagination.link

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.SortField

class LinkPageFilter(

    override val filter: LinkFilter? = null,
    override val limitOffsetAndSort: LimitOffsetAndSort? = null,
    override val sortField: SortField? = null,

    ): PageFilter(limitOffsetAndSort, sortField)
