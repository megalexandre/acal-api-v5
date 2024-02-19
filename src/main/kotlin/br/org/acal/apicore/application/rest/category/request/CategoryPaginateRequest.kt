package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.dto.pagination.category.CategoryPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.pages.SortField
import org.springframework.validation.annotation.Validated

@Validated
data class CategoryPaginateRequest (

    val filter: CategoryFilterRequest? = null,
    val limitOffset: LimitOffset? = null,
    val sortField: SortField? = null,

    ): RequestAdapter<CategoryPageFilter> {

    override fun toEntity(): CategoryPageFilter = CategoryPageFilter(
        filter = filter?.toEntity(),
        limitOffset = limitOffset,
        sortField = sortField,
    )

}