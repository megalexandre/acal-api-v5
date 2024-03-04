package br.org.acal.apicore.application.rest.category.response

import br.org.acal.apicore.domain.entity.Category
import java.math.BigDecimal

data class CategoryGetResponse (
    val id: String,
    val name: String,
    val type: String,
    val total: BigDecimal,
    val values: List<CategoryValuesResponse>,
) {
    constructor(category: Category) : this(
        id = category.id,
        name = category.name,
        type = category.type.value,
        total = category.total,
        values = category.values.map { CategoryValuesResponse(it.name, it.value) }
    )
}



