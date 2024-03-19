package br.org.acal.apicore.resources.datasourceimpl

import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.dto.pagination.address.AddressFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.resources.datasourceimpl.pagination.AddressQuery
import br.org.acal.apicore.resources.datasourceimpl.pagination.LinkQuery
import br.org.acal.apicore.resources.document.AddressDocument
import br.org.acal.apicore.resources.document.adapter.toDocument
import br.org.acal.apicore.resources.document.adapter.toEntity
import br.org.acal.apicore.resources.repository.AddressRepository
import kotlin.jvm.optionals.getOrNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class AddressDataSourceImpl(
    private val repository: AddressRepository,
    private val mongoTemplate: MongoTemplate,
): AddressDataSource {

    override fun findByFilter(filter: DefaultFilter): List<Address> =
        mongoTemplate.find(
            AddressQuery()
                .query(filter as AddressFilter), AddressDocument::class.java).map { it.toEntity() }

    override fun paginateByFilter(filter: PageFilter): Page<Address> {
        val addressPageFilter: LinkPageFilter = filter as LinkPageFilter
        val addressQuery = LinkQuery()
        val pageable = addressQuery.pageRequest(limitOffsetAndSort = addressPageFilter.limitOffsetAndSort ?: LimitOffsetAndSort())
        val query = addressQuery.query(addressPageFilter.filter).with(pageable)
        val countTotalQuery = addressQuery.query(addressPageFilter.filter)

        val list = mongoTemplate.find(query, AddressDocument::class.java)
        val count = mongoTemplate.count(countTotalQuery, AddressDocument::class.java  )
        val page = PageImpl(list, pageable, count)

        return page.toEntity()
    }

    override fun save(t: Address): Address =
        repository.save(t.toDocument()).toEntity()

    override fun save(t: List<Address>) {
        repository.saveAll(t.map { it.toDocument() })
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun findAll(): List<Address> = repository.findAll().map { it.toEntity() }

    override fun findById(id: String): Address? = repository.findById(id).map { it.toEntity() }.getOrNull()


}