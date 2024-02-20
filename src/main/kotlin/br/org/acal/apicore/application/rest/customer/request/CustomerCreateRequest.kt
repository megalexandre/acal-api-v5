package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.application.rest.components.validator.documentNumber.DocumentNumberValidator
import br.org.acal.apicore.application.rest.components.validator.phoneNumber.PhoneNumberValidator
import br.org.acal.apicore.application.rest.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.domain.entity.PhoneNumber
import io.azam.ulidj.ULID.random
import java.time.LocalDate
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerCreateRequest (

    @ULIDValidator
    val id: String?,
    val name: String,
    var birthDay: LocalDate?,
    @DocumentNumberValidator
    val document: String,
    @PhoneNumberValidator
    val phoneNumbers: List<PhoneNumber>?,

    ): RequestAdapter<Customer> {

    override fun toEntity(): Customer = Customer(
        id = id ?: random(),
        name = name,
        documentNumber = DocumentNumber(document),
        birthDay = birthDay,
        phoneNumbers = phoneNumbers,
        active = true,
    )

}

fun List<CustomerCreateRequest>.toEntity(): List<Customer> = map { it.toEntity() }