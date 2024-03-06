package br.org.acal.apicore.application.rest.customer.response

import br.org.acal.apicore.domain.dto.customer.CustomerValidationDocument
import br.org.acal.apicore.domain.entity.Customer
import java.time.LocalDate

class CustomerValidationDocumentResponse(
    val valid: Int,
    val invalid: Int,
    val validCustomer: List<CustomerValidResponse>,
    val inValidCustomer: List<CustomerInvalidResponse>
){
    constructor(customerValidationDocument: CustomerValidationDocument): this(
        valid = customerValidationDocument.valid,
        invalid= customerValidationDocument.invalid,
        validCustomer = customerValidationDocument.validCustomer.map { CustomerValidResponse(it) },
        inValidCustomer = customerValidationDocument.inValidCustomer.map { CustomerInvalidResponse(it) },
    )

    val total: Int
        get() = valid + invalid
}

class CustomerValidResponse(
    val id: String,
    val name: String,
    val birthDay: LocalDate?,
    val document: String,
){
    constructor(customer: Customer) : this(
        id = customer.id,
        name = customer.name,
        birthDay = customer.birthDay,
        document = customer.documentNumber.number
    )
}

class CustomerInvalidResponse(
    val name: String,
    val documentNumber: String,
){
    constructor(customer: Customer): this(
        name = customer.name,
        documentNumber = customer.documentNumber.number
    )
}