package br.org.acal.apicore.resources.document

import br.org.acal.apicore.common.enums.CategoryType
import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "area")
data class CategoryDocument (

    @Id
    val id: String,
    val name: String,
    val type: CategoryType,
    val value: BigDecimal,

)
