package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.InvoiceDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface InvoiceRepository : MongoRepository<InvoiceDocument, String>
