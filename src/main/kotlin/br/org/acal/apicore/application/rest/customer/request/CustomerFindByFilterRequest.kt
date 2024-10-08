package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.domain.dto.pagination.customer.CustomerFilter
import org.springframework.validation.annotation.Validated

@Validated
data class CustomerFindByFilterRequest (

    val id: String? = null,
    val name: String? = null,
    val documentNumber: String? = null,

) {

    fun toEntity(): CustomerFilter = CustomerFilter(
        id = id,
        name = name?.trim(),
        documentNumber = documentNumber?.trim(),
    )

}