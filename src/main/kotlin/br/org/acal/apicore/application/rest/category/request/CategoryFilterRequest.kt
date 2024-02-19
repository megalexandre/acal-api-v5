package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.dto.pagination.category.CategoryFilter

class CategoryFilterRequest(

    val id: String? = null,
    val name: String? = null,
    val type: String? = null,

): RequestAdapter<CategoryFilter> {

    override fun toEntity(): CategoryFilter = CategoryFilter(
        id = id,
        name = name,
        type = CategoryType.get(type),
    )
}

