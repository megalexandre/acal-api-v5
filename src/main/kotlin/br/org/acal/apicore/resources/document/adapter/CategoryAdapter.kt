package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.resources.document.CategoryDocument
import org.springframework.data.domain.Page

fun Category.toDocument() = CategoryDocument(
    id = id,
    name = name,
    type = type,
    value = value,
)

fun CategoryDocument.toEntity() = Category(
    id = id,
    name = name,
    type = type,
    value = value,
)

fun Page<CategoryDocument>.toEntity(): Page<Category> = map { it.toEntity()  }