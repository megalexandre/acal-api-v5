package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.resources.document.LinkDocument
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository

interface LinkRepository : MongoRepository<LinkDocument, String>{

    companion object {
        private const val ACTIVE = "{ \$match: {  active: true } } "
        private const val LOOKUP = "{ \$lookup: { from: \"invoice\", localField: \"_id\", foreignField: \"linkId\",  as: \"invoices\"}} "
        private const val PROJECT_1 = "{\$project: { _id: 1, customer: 1, address: 1, category: 1, suspended: 1, active: 1, _class: 1, references: { \$map: { input: \"\$invoices\", as: \"invoice\", in: \"\$\$invoice.reference\"}}}}"
        private const val MATCH_REFERENCE = " { \$match: { references: { \$nin: [?0] }}} "
    }
    @Aggregation(pipeline = [
        ACTIVE,
        LOOKUP,
        PROJECT_1,
        MATCH_REFERENCE,
    ])
    fun findFindAllWithoutInvoiceForReference(reference: String): List<LinkDocument>

    /*
    {$project: { _id: 1, customer: 1, address: 1, category: 1, suspended: 1, active: 1, _class: 1, references: { $map: { input: "$invoices", as: "invoice", in: "$$invoice.reference"}}}}
    * */



}