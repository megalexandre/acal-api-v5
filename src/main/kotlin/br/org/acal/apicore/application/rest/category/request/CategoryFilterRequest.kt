package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.common.enums.CategoryType.Companion.get
import br.org.acal.apicore.domain.dto.pagination.category.CategoryFilter
import org.springframework.web.bind.annotation.RequestParam

class CategoryFilterRequest(

    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val name: String?,
    @RequestParam(required = false) val type: String?,
    @RequestParam(required = false) val water: String?,
    @RequestParam(required = false) val partner: String?,

    ): RequestAdapter<CategoryFilter> {

    override fun toEntity(): CategoryFilter = CategoryFilter(
        id = id,
        name = name,
        type = get(type),
        water = water,
        partner = partner,
    )
}

