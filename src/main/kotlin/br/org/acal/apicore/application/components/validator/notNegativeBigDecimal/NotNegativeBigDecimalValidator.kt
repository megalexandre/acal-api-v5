package br.org.acal.apicore.application.components.validator.notNegativeBigDecimal

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

class NotNegativeBigDecimalValidator : ConstraintValidator<NotNegativeBigDecimal, BigDecimal> {
    override fun isValid(value: BigDecimal, context: ConstraintValidatorContext?): Boolean =
        value > ZERO
}