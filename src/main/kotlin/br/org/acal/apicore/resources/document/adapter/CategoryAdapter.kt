package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import br.org.acal.apicore.resources.document.CategoryDocument
import br.org.acal.apicore.resources.document.CategoryValuesDocument
import org.springframework.data.domain.Page

fun Category.toDocument() = CategoryDocument(
    id = id,
    name = name,
    type = type,
    values = values.map { CategoryValuesDocument( name = it.name, value = it.value) },
)

fun CategoryDocument.toEntity() = Category(
    id = id,
    name = name,
    type = type,
    values = values.map { CategoryValues( name = it.name, value = it.value) },
)

fun Page<CategoryDocument>.toEntity(): Page<Category> = map { it.toEntity()  }