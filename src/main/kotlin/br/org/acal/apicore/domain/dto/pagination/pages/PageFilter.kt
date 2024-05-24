package br.org.acal.apicore.domain.dto.pagination.pages

open class PageFilter (
    open val limitOffsetAndSort: LimitOffsetAndSort? = null,
    open val sortField: SortField? = null,
    open val filter: DefaultFilter? = null,
)


