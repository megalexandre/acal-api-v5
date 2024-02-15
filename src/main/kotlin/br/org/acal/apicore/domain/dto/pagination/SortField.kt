package br.org.acal.apicore.domain.dto.pagination

import org.springframework.data.domain.Sort

class SortField (
    val field: String?,
    val direction: Sort.Direction?,
)
