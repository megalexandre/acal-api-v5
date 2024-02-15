package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.CustomerDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface CustomerRepository : MongoRepository<CustomerDocument, String> {
    fun findByDocumentNumber(documentNumber: String): CustomerDocument?
}