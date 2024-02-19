package br.org.acal.apicore.resources.datasourceimpl

import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.dto.pagination.link.CategoryFilter
import br.org.acal.apicore.domain.dto.pagination.link.LinkPageFilter
import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.LimitOffset
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.resources.datasourceimpl.pagination.CategoryQuery
import br.org.acal.apicore.resources.datasourceimpl.pagination.LinkQuery
import br.org.acal.apicore.resources.document.CategoryDocument
import br.org.acal.apicore.resources.document.adapter.toDocument
import br.org.acal.apicore.resources.document.adapter.toEntity
import br.org.acal.apicore.resources.repository.CategoryRepository
import kotlin.jvm.optionals.getOrNull
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class CategoryDataSourceImpl(
    private val repository: CategoryRepository,
    private val mongoTemplate: MongoTemplate,
): CategoryDataSource {

    override fun findByFilter(filter: DefaultFilter): List<Category> =
        mongoTemplate.find(
            CategoryQuery()
                .query(filter as CategoryFilter), CategoryDocument::class.java).map { it.toEntity() }

    override fun paginateByFilter(filter: PageFilter): Page<Category> {
        val categoryPageFilter: LinkPageFilter = filter as LinkPageFilter
        val categoryQuery = LinkQuery()
        val pageable = categoryQuery.pageRequest(limitOffset = categoryPageFilter.limitOffset ?: LimitOffset())
        val query = categoryQuery.query(categoryPageFilter.filter).with(pageable)
        val countTotalQuery = categoryQuery.query(categoryPageFilter.filter)

        val list = mongoTemplate.find(query, CategoryDocument::class.java)
        val count = mongoTemplate.count(countTotalQuery, CategoryDocument::class.java  )
        val page = PageImpl(list, pageable, count)

        return page.toEntity()
    }

    override fun save(t: Category): Category =
        repository.save(t.toDocument()).toEntity()

    override fun save(t: List<Category>) {
        repository.saveAll(t.map { it.toDocument() })
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

    override fun findAll(): List<Category> = repository.findAll().map { it.toEntity() }

    override fun findById(id: String): Category? = repository.findById(id).map { it.toEntity() }.getOrNull()

}