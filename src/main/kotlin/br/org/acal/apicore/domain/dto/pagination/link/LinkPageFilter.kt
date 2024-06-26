package br.org.acal.apicore.domain.dto.pagination.link

import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter

class LinkPageFilter(

    override val filter: LinkFilter? = null,
    override val limitOffsetAndSort: LimitOffsetAndSort? = null,

): PageFilter(limitOffsetAndSort)
