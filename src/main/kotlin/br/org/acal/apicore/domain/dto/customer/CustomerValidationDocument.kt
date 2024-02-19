package br.org.acal.apicore.domain.dto.customer

import br.org.acal.apicore.domain.entity.Customer

class CustomerValidationDocument(
    val valid: Int,
    val invalid: Int,
    val validCustomer: List<Customer>,
    val inValidCustomer: List<Customer>
)