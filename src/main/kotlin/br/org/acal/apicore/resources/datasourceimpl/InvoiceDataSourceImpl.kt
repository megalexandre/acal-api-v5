package br.org.acal.apicore.resources.datasourceimpl

import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.dto.pagination.invoice.InvoiceFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.resources.datasourceimpl.pagination.InvoiceQuery
import br.org.acal.apicore.resources.datasourceimpl.pagination.LinkQuery
import br.org.acal.apicore.resources.document.InvoiceDocument
import br.org.acal.apicore.resources.document.adapter.toDocument
import br.org.acal.apicore.resources.document.adapter.toEntity
import br.org.acal.apicore.resources.repository.InvoiceRepository
import kotlin.jvm.optionals.getOrNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class InvoiceDataSourceImpl(
    private val repository: InvoiceRepository,
    private val mongoTemplate: MongoTemplate,
): InvoiceDataSource {

    override fun findByFilter(filter: DefaultFilter): List<Invoice> =
        mongoTemplate.find(
            InvoiceQuery()
                .query(filter as InvoiceFilter), InvoiceDocument::class.java).map { it.toEntity() }

    override fun paginateByFilter(filter: PageFilter): Page<Invoice> {
        val invoicePageFilter: LinkPageFilter = filter as LinkPageFilter
        val invoiceQuery = LinkQuery()
        val pageable = invoiceQuery.pageRequest(limitOffset = invoicePageFilter.limitOffset ?: LimitOffset())
        val query = invoiceQuery.query(invoicePageFilter.filter).with(pageable)
        val countTotalQuery = invoiceQuery.query(invoicePageFilter.filter)

        val list = mongoTemplate.find(query, InvoiceDocument::class.java)
        val count = mongoTemplate.count(countTotalQuery, InvoiceDocument::class.java  )
        val page = PageImpl(list, pageable, count)

        return page.toEntity()
    }

    override fun save(t: Invoice): Invoice =
        repository.save(t.toDocument()).toEntity()

    override fun save(t: List<Invoice>) {
        repository.saveAll(t.map { it.toDocument() })
    }
    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun findAll(): List<Invoice> = repository.findAll().map { it.toEntity() }

    override fun findById(id: String): Invoice? = repository.findById(id).map { it.toEntity() }.getOrNull()

}