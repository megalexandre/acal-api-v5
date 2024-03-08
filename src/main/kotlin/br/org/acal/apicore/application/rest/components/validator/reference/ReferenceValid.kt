package br.org.acal.apicore.application.rest.components.validator.reference

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Target(FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(RUNTIME)
@Constraint(validatedBy = [ReferenceValidator::class])
annotation class ReferenceValid(
    val message: String = "{}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)