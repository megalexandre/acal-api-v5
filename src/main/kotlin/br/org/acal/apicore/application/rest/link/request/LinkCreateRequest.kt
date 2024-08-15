package br.org.acal.apicore.application.rest.link.request

import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.domain.entity.LinkCreate
import org.springframework.validation.annotation.Validated

@Validated
data class LinkCreateRequest (

    @field:ULIDValidator
    val customerId: String,

    @field:ULIDValidator
    val addressId: String,

    @field:ULIDValidator
    val categoryId: String,

){

    fun toEntity(): LinkCreate = LinkCreate(
        customerId = customerId,
        addressId = addressId,
        categoryId = categoryId,
    )

}
