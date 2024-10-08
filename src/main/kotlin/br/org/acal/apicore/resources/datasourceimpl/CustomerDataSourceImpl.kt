package br.org.acal.apicore.resources.datasourceimpl

import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.dto.pagination.customer.CustomerFilter
import br.org.acal.apicore.domain.dto.pagination.customer.CustomerPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.resources.datasourceimpl.pagination.CustomerQuery
import br.org.acal.apicore.resources.document.CustomerDocument
import br.org.acal.apicore.resources.document.adapter.toDocument
import br.org.acal.apicore.resources.document.adapter.toEntity
import br.org.acal.apicore.resources.repository.CustomerRepository
import kotlin.jvm.optionals.getOrNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class CustomerDataSourceImpl(
    private val repository: CustomerRepository,
    private val mongoTemplate: MongoTemplate,
): CustomerDataSource {

    override fun findByDocument(documentNumber: DocumentNumber): Customer? =
        repository.findByDocumentNumber(documentNumber.number)?.toEntity()

    override fun findByFilter(filter: DefaultFilter): List<Customer> =
        mongoTemplate.find(
            CustomerQuery()
                .query(filter as CustomerFilter), CustomerDocument::class.java).map { it.toEntity() }

    override fun paginateByFilter(filter: PageFilter): Page<Customer> {
        require(filter is CustomerPageFilter) {
            "Filtro deve ser do tipo CustomerPageFilter"
        }

        val customerQuery = CustomerQuery()

        val pageable = customerQuery.pageRequest(filter.limitOffsetAndSort)

        val query = customerQuery.query(filter.filter).with(pageable)

        val list = mongoTemplate.find(query, CustomerDocument::class.java)
        val count = mongoTemplate.count(customerQuery.query(filter.filter), CustomerDocument::class.java)

        val page = PageImpl(list, pageable, count)

        return page.toEntity()
    }


    override fun save(t: Customer): Customer =
        repository.save(t.toDocument()).toEntity()

    override fun saveAll(t: List<Customer>) {
        repository.saveAll(t.map { it.toDocument() })
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun findAll(): List<Customer> = repository.findAll().map { it.toEntity() }

    override fun findById(id: String): Customer? = repository.findById(id).map { it.toEntity() }.getOrNull()

}