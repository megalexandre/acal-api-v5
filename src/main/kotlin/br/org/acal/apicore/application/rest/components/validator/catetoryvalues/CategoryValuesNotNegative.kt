package br.org.acal.apicore.application.rest.components.validator.catetoryvalues

import br.org.acal.apicore.application.rest.components.validator.reference.ReferenceValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ReferenceValidator::class])
annotation class CategoryValuesNotNegative(
    val message: String = "O valor não pode ser negativo",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)