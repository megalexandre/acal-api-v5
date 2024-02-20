package br.org.acal.apicore.application.rest.link.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.LinkCreate
import org.springframework.validation.annotation.Validated

@Validated
data class LinkCreateLotRequest (

    val id: String,
    val customer: String,
    val address: String,
    val category: String,

): RequestAdapter<LinkCreate> {

    override fun toEntity(): LinkCreate = LinkCreate(
        id = id,
        customer = customer,
        address = address,
        category = category,
    )

}

fun List<LinkCreateLotRequest>.toEntity(): List<LinkCreate> = map { it.toEntity() }