package br.org.acal.apicore.application.rest.link.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.Link
import io.azam.ulidj.ULID.random
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
        suspended = false,
        active = true,
    )

}

fun List<LinkCreateRequest>.toEntity(): List<Link> = map { it.toEntity() }