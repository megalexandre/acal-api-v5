package br.org.acal.apicore.application.rest.components.validator.invoiceNumber

import br.org.acal.apicore.common.enums.Fixtures.Companion.SEPARATOR
import br.org.acal.apicore.infrastructure.Sl4jLogger
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.Month
import java.time.Year

class InvoiceNumberConstraint : ConstraintValidator<InvoiceNumberValidator, String>, Sl4jLogger() {

    override fun isValid(value: String, cxt: ConstraintValidatorContext): Boolean =
        runCatching {
            val groups = value.split(SEPARATOR)
            val year: String = groups[0]
            val month: String = groups[1]
            val number: String = groups[2]
            Year.of(year.toInt())
            Month.of(month.toInt())
            number.toInt()
        }.isSuccess


}