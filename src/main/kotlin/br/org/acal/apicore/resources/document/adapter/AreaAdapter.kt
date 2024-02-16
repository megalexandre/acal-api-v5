package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Area
import br.org.acal.apicore.resources.document.AreaDocument
import org.springframework.data.domain.Page

fun Area.toDocument() = AreaDocument(
    id = id,
    name = name,
)

fun AreaDocument.toEntity() = Area(
    id = id,
    name = name,
)

fun Page<AreaDocument>.toEntity() = map { it.toEntity()  }