package br.org.acal.apicore.application.rest.components.validator.reference

import br.org.acal.apicore.domain.entity.Reference
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ReferenceValidator : ConstraintValidator<ReferenceValid, String> {
    override fun isValid(refence: String, context: ConstraintValidatorContext?): Boolean =
        runCatching {
            Reference.of(refence)
        }.isSuccess
}