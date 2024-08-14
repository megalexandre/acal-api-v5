package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.components.validator.documentNumber.DocumentNumberValidator
import br.org.acal.apicore.application.components.validator.phoneNumber.PhoneNumberValidator
import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.domain.entity.PhoneNumber
import java.time.LocalDate
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerUpdateRequest (

    @ULIDValidator
    val id: String,
    val name: String,
    var birthDay: LocalDate?,
    @DocumentNumberValidator
    val document: String,
    @PhoneNumberValidator
    val phoneNumbers: List<PhoneNumber>?,
) {

    fun toEntity(): Customer = Customer(
        id = id,
        name = name,
        documentNumber = DocumentNumber(document),
        birthDay = birthDay,
        phoneNumbers = phoneNumbers,
        active = true,
    )

}

fun List<CustomerUpdateRequest>.toEntity(): List<Customer> = map { it.toEntity() }