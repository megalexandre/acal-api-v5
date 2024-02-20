package br.org.acal.apicore.application.rest.components.validator.phoneNumber

import br.org.acal.apicore.domain.entity.PhoneNumber
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.error
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ValidPhoneNumber : ConstraintValidator<PhoneNumberValidator, List<PhoneNumber>?>, Sl4jLogger()  {

    override fun isValid(value: List<PhoneNumber>?, context: ConstraintValidatorContext): Boolean {
        context.disableDefaultConstraintViolation()

        return value?.let { phoneNumbers ->

            val hasExactlyOnePreferential = phoneNumbers.count { it.preferential } == 1
            val hasDuplications = phoneNumbers.distinctBy { it.number }.size != phoneNumbers.size

            if (!hasExactlyOnePreferential) {
                context.buildConstraintViolationWithTemplate("Deve haver exatamente 1 número preferencial.")
                    .addConstraintViolation()

                logger.error { "This phoneNumber list has two or more preferential numbers: $phoneNumbers" }
            }

            if (hasDuplications) {
                context.buildConstraintViolationWithTemplate("Números duplicados")
                    .addConstraintViolation()

                logger.error { "This phoneNumber list has duplications: $phoneNumbers" }
            }

            return hasExactlyOnePreferential && !hasDuplications

        } ?: true
    }

}