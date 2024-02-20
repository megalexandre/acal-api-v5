package br.org.acal.apicore.application.rest.components.validator.ulid

import io.azam.ulidj.ULID
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ValidULID : ConstraintValidator<ULIDValidator, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean =
        value?.let { ULID.isValid(it) } ?: true
}