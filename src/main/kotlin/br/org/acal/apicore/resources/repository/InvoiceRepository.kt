package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.InvoiceDocument
import java.util.Optional
import org.springframework.data.mongodb.repository.MongoRepository

interface InvoiceRepository : MongoRepository<InvoiceDocument, String>{
    fun findByLinkIdAndReference(linkId: String, reference: String): Optional<InvoiceDocument>
}
