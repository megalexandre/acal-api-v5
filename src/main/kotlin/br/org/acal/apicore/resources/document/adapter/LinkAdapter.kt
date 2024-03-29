package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.resources.document.LinkDocument
import org.springframework.data.domain.Page

fun Link.toDocument() = LinkDocument(
    id = id,
    customer = customer.toDocument(),
    address = address.toDocument(),
    category = category.toDocument() ,
    suspended = suspended,
    active = active,
)

fun LinkDocument.toEntity() = Link(
    id = id,
    customer = customer.toEntity(),
    address = address.toEntity(),
    category = category.toEntity(),
    suspended = suspended ?: false,
    active = active ?: true,
)

fun Page<LinkDocument>.toEntity() = map { it.toEntity()  }