package br.org.acal.apicore.application.rest.link.request

import br.org.acal.apicore.domain.entity.LinkCreate
import org.springframework.validation.annotation.Validated

@Validated
data class LinkCreateLotRequest (

    val customerId: String,
    val addressId: String,
    val categoryId: String,

) {

    fun toEntity(): LinkCreate = LinkCreate(
        customerId = customerId,
        addressId = addressId,
        categoryId = categoryId,
    )

}

fun List<LinkCreateLotRequest>.toEntity(): List<LinkCreate> = map { it.toEntity() }