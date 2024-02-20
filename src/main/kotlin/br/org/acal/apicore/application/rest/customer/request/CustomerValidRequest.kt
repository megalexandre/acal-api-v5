package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.domain.entity.PhoneNumber
import java.time.LocalDate
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerValidRequest (

    val id: String,
    val name: String,
    var birthDay: LocalDate?,
    val document: String,
    val phoneNumbers: List<PhoneNumber>?,

    ): RequestAdapter<Customer> {

    override fun toEntity(): Customer = Customer(
        id = id,
        name = name,
        documentNumber = DocumentNumber(document),
        birthDay = birthDay,
        phoneNumbers = phoneNumbers,
        active = true,
    )

}
fun List<CustomerValidRequest>.toEntity(): List<Customer> = map { it.toEntity() }