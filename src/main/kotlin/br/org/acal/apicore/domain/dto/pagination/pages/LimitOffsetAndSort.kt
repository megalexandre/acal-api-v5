package br.org.acal.apicore.domain.dto.pagination.pages

import org.springframework.data.domain.Sort.Direction

class LimitOffsetAndSort (
    val offset: Int? = 0,
    val size: Int? = 10,
    val field: String = "id",
    val direction: Direction = Direction.ASC,
)
