package br.org.acal.apicore.application.components.validator.catetoryvalues

import br.org.acal.apicore.application.rest.category.request.CategoryCreateValuesRequest
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.math.BigDecimal.ZERO

class CategoryValuesNotNegativeValidator : ConstraintValidator<CategoryValuesNotNegative, List<CategoryCreateValuesRequest>> {
    override fun isValid(values:List<CategoryCreateValuesRequest>, context: ConstraintValidatorContext?): Boolean =
        values.any { it.value > ZERO }
}