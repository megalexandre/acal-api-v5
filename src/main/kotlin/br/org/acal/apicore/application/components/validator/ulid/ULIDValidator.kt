package br.org.acal.apicore.application.components.validator.ulid

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER
import kotlin.reflect.KClass

@Target(FIELD, VALUE_PARAMETER)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ValidULID::class])
annotation class ULIDValidator(
    val message: String = "Invalid ULID",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)