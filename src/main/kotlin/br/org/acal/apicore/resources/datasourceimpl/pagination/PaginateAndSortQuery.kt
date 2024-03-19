package br.org.acal.apicore.resources.datasourceimpl.pagination

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.mongodb.core.query.Query

abstract class PaginateAndSortQuery<T: DefaultFilter> {

    private val id = "id"
    private val defaultSort = Sort.by(ASC,id)

    open fun pageRequest(limitOffsetAndSort: LimitOffsetAndSort): PageRequest =
        PageRequest.of(
            limitOffsetAndSort.offset ?: 0,
            limitOffsetAndSort.size ?: 10,
            Sort.by(limitOffsetAndSort.direction, limitOffsetAndSort.field)

        )

    abstract fun query(filter: T?): Query
}



