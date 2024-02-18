package br.org.acal.apicore.application.rest.category.response

import br.org.acal.apicore.domain.entity.Category
import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP

data class CategoryFindAllResponse (
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
        total = category.total.setScale(2, HALF_UP),
        values = category.values.map { CategoryValuesResponse(it.name, it.value) }
    )
}

data class CategoryValuesResponse(
    val name: String,
    val value: BigDecimal,
)


