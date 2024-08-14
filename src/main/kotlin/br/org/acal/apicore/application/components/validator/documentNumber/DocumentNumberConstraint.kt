package br.org.acal.apicore.application.components.validator.documentNumber

import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.error
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class DocumentNumberConstraint : ConstraintValidator<DocumentNumberValidator, String>, Sl4jLogger() {

    override fun isValid(value: String, cxt: ConstraintValidatorContext): Boolean = DocumentNumber(value).isValid
        .also {
            if(!it){
                logger.error { "Document Number $value is not valid" }
            }
        }

}