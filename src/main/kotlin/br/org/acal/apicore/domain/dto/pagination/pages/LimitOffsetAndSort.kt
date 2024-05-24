package br.org.acal.apicore.domain.dto.pagination.pages

class LimitOffsetAndSort (
    val offset: Int? = 0,
    val size: Int? = 10,
    val sortField: SortField? = null,
)
