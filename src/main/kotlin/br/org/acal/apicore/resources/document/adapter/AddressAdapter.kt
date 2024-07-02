package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.resources.document.AddressDocument
import org.springframework.data.domain.Page
fun Address.toDocument() = AddressDocument(
    id = id,
    area = area.toDocument(),
    number = number,
    letter = letter,
    hasHydrometer = hasHydrometer,
    active = active,
)
fun AddressDocument.toEntity() = Address(
    id = id,
    area = area.toEntity(),
    number = number,
    letter = letter,
    hasHydrometer = hasHydrometer,
    active = active ?: false,
)

fun Page<AddressDocument>.toEntity() = map { it.toEntity()  }