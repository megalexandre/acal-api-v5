package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.components.validator.phoneNumber.PhoneNumberValidator
import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.domain.entity.PhoneNumber
import io.azam.ulidj.ULID.random
import java.time.LocalDate
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerMigrateRequest (

    @ULIDValidator
    val id: String?,
    val name: String,
    var birthDay: LocalDate?,
    val document: String,
    @PhoneNumberValidator
    val phoneNumbers: List<PhoneNumber>?,
    val active: Boolean?,

) {

   fun toEntity(): Customer = Customer(
        id = id ?: random(),
        name = name,
        documentNumber = DocumentNumber(document),
        birthDay = birthDay,
        phoneNumbers = phoneNumbers,
        active = active ?: true,
    )

}

fun List<CustomerMigrateRequest>.toEntity(): List<Customer> = map { it.toEntity() }