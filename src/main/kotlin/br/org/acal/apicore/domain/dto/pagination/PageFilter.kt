package br.org.acal.apicore.domain.dto.pagination

open class PageFilter (
    open val limitOffset: LimitOffset? = null,
    open val sortField: SortField? = null,
    open val filter: DefaultFilter? = null,
)

