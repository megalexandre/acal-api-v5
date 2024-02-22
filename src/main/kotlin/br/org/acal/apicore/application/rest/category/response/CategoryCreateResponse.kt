package br.org.acal.apicore.application.rest.category.response

import br.org.acal.apicore.domain.entity.Category

data class CategoryCreateResponse (
    val id: String,
    val name: String,
    val type: String,
    val values: List<CategoryValuesResponse>,
) {
    constructor(category: Category) : this(
        id = category.id,
        name = category.name,
        type = category.type.value,
        values = category.values.map { CategoryValuesResponse(it.name, it.value) }
    )
}



