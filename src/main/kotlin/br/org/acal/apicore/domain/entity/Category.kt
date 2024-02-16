package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.CategoryType
import java.math.BigDecimal

data class Category (

    val id: String,
    val name: String,
    val type: CategoryType,
    val value: BigDecimal,

)
