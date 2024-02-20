package br.org.acal.apicore.application.rest.components.validator.invoiceNumber

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FIELD, FUNCTION)
@Retention(RUNTIME)
@Constraint(validatedBy = [InvoiceNumberConstraint::class])
annotation class InvoiceNumberValidator(
    val message: String = "{}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)