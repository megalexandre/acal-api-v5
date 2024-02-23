package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import io.azam.ulidj.ULID.random
import jakarta.validation.constraints.DecimalMin
import java.math.BigDecimal
import org.springframework.validation.annotation.Validated

@Validated
data class CategoryCreateRequest (

    val name: String,
    val type: CategoryType,
    val values: List<CategoryCreateValuesRequest>,

): RequestAdapter<Category> {

    override fun toEntity(): Category = Category(
        id = random(),
        name = name,
        type = type,
        values = values.map { CategoryValues(name = it.name, value = it.value) }
    )
}

@Validated
data class CategoryCreateValuesRequest(
    val name: String,
    @DecimalMin(value = "0.0", inclusive = false,  message = "category value can't be less then zero")
    val value: BigDecimal,
)

fun List<CategoryCreateRequest>.toEntity(): List<Category> = map { it.toEntity() }