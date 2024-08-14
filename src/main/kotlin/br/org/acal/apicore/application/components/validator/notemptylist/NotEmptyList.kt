package br.org.acal.apicore.application.components.validator.notemptylist

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotEmptyListValidator::class])
annotation class NotEmptyList(
    val message: String = "A lista não pode estar vazia",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)