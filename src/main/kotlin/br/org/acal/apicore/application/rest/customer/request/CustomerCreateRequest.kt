package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.application.rest.components.validator.documentNumber.DocumentNumberValidator
import br.org.acal.apicore.application.rest.components.validator.phoneNumber.PhoneNumberValidator
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.domain.entity.PhoneNumber
import io.azam.ulidj.ULID.random
import java.time.LocalDate
import java.time.LocalDateTime.now
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerCreateRequest (
    val name: String,
    var birthDay: LocalDate?,

    @DocumentNumberValidator
    val documentNumber: String,

    @PhoneNumberValidator
    val phoneNumbers: List<PhoneNumber>?,

): RequestAdapter<Customer> {

    override fun toEntity(): Customer = Customer(
        id = random(),
        name = name,
        documentNumber = DocumentNumber(documentNumber),
        birthDay = birthDay,
        phoneNumbers = phoneNumbers,
        createdAt = now(),
        createdBy = "",
        active = true,
    )

}