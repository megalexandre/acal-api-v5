package br.org.acal.apicore.application.rest.components.validator.documentNumber

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class DigitsOnlyConstraint : ConstraintValidator<DocumentNumberValidator, String> {

    companion object{
        private const val DIGITS_ONLY_REGEX = "[0-9]+"
    }

    override fun isValid(value: String, cxt: ConstraintValidatorContext): Boolean =
        value.matches(Regex(DIGITS_ONLY_REGEX))
}