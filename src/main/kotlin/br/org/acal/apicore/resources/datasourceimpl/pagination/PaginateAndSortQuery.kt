package br.org.acal.apicore.resources.datasourceimpl.pagination

import br.org.acal.apicore.domain.dto.pagination.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.LimitOffset
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.mongodb.core.query.Query

abstract class PaginateAndSortQuery<T: DefaultFilter> {

    private val id = "id"
    private val defaultSort = Sort.by(ASC,id)

    open fun pageRequest(limitOffset: LimitOffset): PageRequest =
        PageRequest.of(limitOffset.offset ?: 0,limitOffset.size ?: 10)

    abstract fun query(filter: T?): Query
}



