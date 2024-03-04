package br.org.acal.apicore.application.rest.components.validator.ulid

import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import br.org.acal.apicore.infrastructure.kotlin.extensions.alsoFalse
import io.azam.ulidj.ULID
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ValidULID : ConstraintValidator<ULIDValidator, String>, Sl4jLogger()  {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean =
        value?.let {
            ULID.isValid(it).alsoFalse {
                logger.info { "this: $it is not a valid id" }
            }
        } ?: true
}