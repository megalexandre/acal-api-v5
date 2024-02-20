package br.org.acal.apicore.resources.document.adapter

import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.resources.document.CustomerDocument
import org.springframework.data.domain.Page

fun Customer.toDocument() = CustomerDocument(
    id = id,
    name = name,
    documentNumber = documentNumber.number,
    phoneNumbers = phoneNumbers,
    birthDay = birthDay,
    active = active
)

fun CustomerDocument.toEntity() = Customer(
    id = id,
    name = name,
    documentNumber = DocumentNumber(documentNumber),
    birthDay = birthDay,
    phoneNumbers = phoneNumbers,
    active = active,
)

fun Page<CustomerDocument>.toEntity() = map { it.toEntity()  }