package br.org.acal.apicore.application.rest.components.validator.notemptylist

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotEmptyListValidator : ConstraintValidator<NotEmptyList, List<*>> {
    override fun isValid(value: List<*>, context: ConstraintValidatorContext): Boolean =
        value.isNotEmpty()
}