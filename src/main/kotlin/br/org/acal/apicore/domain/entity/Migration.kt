package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType
import br.org.acal.apicore.domain.entity.interfaces.Entity
import java.time.LocalDate
import java.time.LocalDateTime

data class Migration (

    val customer: Customer,
    val address: Address,
    val category: Category,

)