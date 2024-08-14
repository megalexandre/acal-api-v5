package br.org.acal.apicore.resources.datasourceimpl.pagination

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.PageRequest.of
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.by
import org.springframework.data.mongodb.core.query.Query

abstract class PaginateAndSortQuery<T: DefaultFilter> {

    private val id = "id"
    private val defaultSort = by(ASC, id)
    open fun pageRequest(limitOffsetAndSort: LimitOffsetAndSort?): PageRequest =
        (limitOffsetAndSort ?: LimitOffsetAndSort()).run {
            of(this.startAt, this.pageSize, by(this.direction, this.field))
        }

    abstract fun query(filter: T?): Query
}

