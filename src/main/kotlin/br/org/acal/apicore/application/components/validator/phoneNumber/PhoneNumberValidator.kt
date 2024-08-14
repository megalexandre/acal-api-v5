package br.org.acal.apicore.application.components.validator.phoneNumber

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.reflect.KClass

@MustBeDocumented
@Target(FIELD, FUNCTION)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidPhoneNumber::class])
annotation class PhoneNumberValidator(
    val message: String = "Invalid phone number",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)