package br.org.acal.apicore.application.rest.customer.request

import br.org.acal.apicore.domain.dto.pagination.customer.CustomerFilter
import java.time.LocalDate
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestParam

@Validated
class CustomerFilterRequest(
    @RequestParam(required = false) val id: String?,
    @RequestParam(required = false) val name: String?,
    @RequestParam(required = false) val documentNumber: String?,
    @RequestParam(required = false) val birthDay: LocalDate? = null,
    @RequestParam(required = false) val active: Boolean? = null,
){
    fun toCustomerFilter() = CustomerFilter(
        id = this.id,
        name = name,
        documentNumber = documentNumber,
        birthDay = birthDay,
        active = active
    )
}