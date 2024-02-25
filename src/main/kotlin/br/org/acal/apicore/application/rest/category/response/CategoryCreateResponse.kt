package br.org.acal.apicore.application.rest.category.response

import br.org.acal.apicore.domain.entity.Category

data class CategoryCreateResponse (
    val id: String,
) {
    constructor(category: Category) : this(
        id = category.id,
    )
}



