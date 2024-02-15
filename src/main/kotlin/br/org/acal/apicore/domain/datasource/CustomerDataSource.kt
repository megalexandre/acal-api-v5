package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber

interface CustomerDataSource: CustomDataSource<Customer> {
    fun findByDocument(documentNumber: DocumentNumber): Customer?
}