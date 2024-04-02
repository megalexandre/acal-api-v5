package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.pages.PageFilter
import org.springframework.data.domain.Page

interface CustomDataSource<T> {
    fun save(t: T): T
    fun saveAll(t: List<T>)
    fun delete(id: String)
    fun findAll(): List<T>
    fun findById(id: String): T?
    fun findByFilter(filter: DefaultFilter): List<T>
    fun paginateByFilter(filter: PageFilter): Page<T>
}

