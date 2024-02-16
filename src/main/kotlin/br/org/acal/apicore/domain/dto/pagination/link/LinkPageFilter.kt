package br.org.acal.apicore.domain.dto.pagination.link

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.SortField

class LinkPageFilter(

    override val filter: LinkFilter? = null,
    override val limitOffset: LimitOffset? = null,
    override val sortField: SortField? = null,

): PageFilter(limitOffset, sortField)
