package br.org.acal.apicore.domain.dto.pagination.pages

import org.springframework.data.domain.Sort.Direction

class SortField (
    val field: String?,
    val direction: Direction?,
)
