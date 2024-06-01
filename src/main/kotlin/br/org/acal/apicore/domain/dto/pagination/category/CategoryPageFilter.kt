package br.org.acal.apicore.domain.dto.pagination.category

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter

class CategoryPageFilter(

    override val filter: CategoryFilter? = null,
    override val limitOffsetAndSort: LimitOffsetAndSort? = null,

): PageFilter(limitOffsetAndSort)
