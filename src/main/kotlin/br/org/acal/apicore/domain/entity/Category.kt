package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.common.util.sum
import java.math.BigDecimal

data class Category (
    val id: String,
    val name: String,
    val type: CategoryType,
    val values: List<CategoryValues>,
){
    val total: BigDecimal
        get() = values.map { it.value }.sum()
}

data class CategoryValues(
    val name: String,
    val value: BigDecimal,
)
