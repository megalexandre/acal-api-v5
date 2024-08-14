package br.org.acal.apicore.application.rest.category.request

import br.org.acal.apicore.application.components.validator.catetoryvalues.CategoryValuesNotNegative
import br.org.acal.apicore.application.components.validator.notemptylist.NotEmptyList
import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import io.azam.ulidj.ULID.random
import jakarta.validation.Valid
import java.math.BigDecimal
import org.springframework.validation.annotation.Validated

@Validated
data class CategoryCreateRequest (

    @ULIDValidator
    val id: String?,
    val name: String,
    val type: CategoryType,
    @Valid
    @NotEmptyList
    @CategoryValuesNotNegative
    val values: List<CategoryCreateValuesRequest>,

) {

    fun toEntity(): Category = Category(
        id = id ?: random(),
        name = name,
        type = type,
        values = values.map { CategoryValues(name = it.name, value = it.value ) }
    )
}

@Validated
data class CategoryCreateValuesRequest(
    val name: String,
    val value: BigDecimal,
)

fun List<CategoryCreateRequest>.toEntity(): List<Category> = map { it.toEntity() }