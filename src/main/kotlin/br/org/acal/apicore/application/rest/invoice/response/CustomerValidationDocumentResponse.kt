package br.org.acal.apicore.application.rest.invoice.response

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
        invalid= customerValidationDocument.valid,
        validCustomer = customerValidationDocument.validCustomer.map { CustomerValidResponse(it) },
        inValidCustomer = customerValidationDocument.inValidCustomer.map { CustomerInvalidResponse(it) },
    )

    val total: Int
        get() = valid + invalid
}

class CustomerValidResponse(
    val name: String,
    var birthDay: LocalDate?,
    val documentNumber: String,
){
    constructor(customer: Customer) : this(
        name = customer.name,
        birthDay = customer.birthDay,
        documentNumber = customer.documentNumber.number
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