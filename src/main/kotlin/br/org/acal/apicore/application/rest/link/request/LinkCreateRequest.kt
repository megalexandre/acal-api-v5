package br.org.acal.apicore.application.rest.link.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.application.rest.components.validator.documentNumber.DocumentNumberValidator
import br.org.acal.apicore.application.rest.components.validator.phoneNumber.PhoneNumberValidator
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.domain.entity.PhoneNumber
import io.azam.ulidj.ULID.random
import java.time.LocalDate
import java.time.LocalDateTime.now
import org.springframework.validation.annotation.Validated

@Validated
data class LinkCreateRequest (

    val customer: Customer,
    val address: Address,
    val category: Category,

): RequestAdapter<Link> {

    override fun toEntity(): Link = Link(
        id = random(),
        customer = customer,
        address = address,
        category = category,
    )

}

fun List<LinkCreateRequest>.toEntity(): List<Link> = map { it.toEntity() }