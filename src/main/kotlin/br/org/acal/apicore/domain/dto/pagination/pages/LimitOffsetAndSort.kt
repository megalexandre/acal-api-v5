package br.org.acal.apicore.domain.dto.pagination.pages

import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC

class LimitOffsetAndSort (
    val offset: Int? = 0,
    val size: Int? = 10,
    val sortField: SortField? = null,

){
    val startAt: Int
        get() = offset ?: 0

    val pageSize: Int
        get() = size ?: 10

    val direction: Sort.Direction
        get() = sortField?.direction ?: ASC

    val field: String
        get() = sortField?.field ?: "id"

}
