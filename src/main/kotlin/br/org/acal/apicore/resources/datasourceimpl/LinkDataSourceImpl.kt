package br.org.acal.apicore.resources.datasourceimpl

import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.dto.pagination.link.LinkFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffsetAndSort
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.Reference
import br.org.acal.apicore.resources.datasourceimpl.pagination.LinkQuery
import br.org.acal.apicore.resources.document.LinkDocument
import br.org.acal.apicore.resources.document.adapter.toDocument
import br.org.acal.apicore.resources.document.adapter.toEntity
import br.org.acal.apicore.resources.repository.LinkRepository
import kotlin.jvm.optionals.getOrNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service


@Service
class LinkDataSourceImpl(
    private val repository: LinkRepository,
    private val mongoTemplate: MongoTemplate,
): LinkDataSource {


    override fun findByFilter(filter: DefaultFilter): List<Link> =
        mongoTemplate.find(
            LinkQuery()
                .query(filter as LinkFilter), LinkDocument::class.java).map { it.toEntity() }

    override fun paginateByFilter(filter: PageFilter): Page<Link> {
        val linkPageFilter: LinkPageFilter = filter as LinkPageFilter
        val linkQuery = LinkQuery()
        val pageable = linkQuery.pageRequest(limitOffsetAndSort = linkPageFilter.limitOffsetAndSort ?: LimitOffsetAndSort())
        val query = linkQuery.query(linkPageFilter.filter).with(pageable)
        val countTotalQuery = linkQuery.query(linkPageFilter.filter)

        val list = mongoTemplate.find(query, LinkDocument::class.java)
        val count = mongoTemplate.count(countTotalQuery, LinkDocument::class.java  )
        val page = PageImpl(list, pageable, count)

        return page.toEntity()
    }


    override fun findFindAllWithoutInvoiceForReferenceUsecase(input: Reference): List<Link> =
        repository.findFindAllWithoutInvoiceForReference(input.value).map { it.toEntity() }


    override fun save(t: Link): Link =
        repository.save(t.toDocument()).toEntity()

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun saveAll(t: List<Link>) {
        repository.saveAll(t.map { it.toDocument() })
    }

    override fun findAll(): List<Link> = repository.findAll().map { it.toEntity() }

    override fun findById(id: String): Link? = repository.findById(id).map { it.toEntity() }.getOrNull()

}