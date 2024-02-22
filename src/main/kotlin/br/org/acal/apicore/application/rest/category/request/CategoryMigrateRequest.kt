package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import io.azam.ulidj.ULID.random
import java.math.BigDecimal
import org.springframework.validation.annotation.Validated

@Validated
data class CategoryMigrateRequest (

    val id: String?,
    val name: String,
    val type: CategoryType,

    val water: BigDecimal?,
    val partnership: BigDecimal?

): RequestAdapter<Category> {
     val getValues: List<CategoryValues>
         get() = listOf(
             CategoryValues(name = "water", value = water?: BigDecimal.ZERO),
             CategoryValues(name = "partnership", value = partnership?: BigDecimal.ZERO)
         )

    override fun toEntity(): Category = Category(
        id = id ?: random(),
        name = name,
        type = type,
        values = getValues,
    )
}

data class CategoryValuesRequest(
    val name: String,
    val value: BigDecimal,
)

fun List<CategoryMigrateRequest>.toEntity(): List<Category> = map { it.toEntity() }