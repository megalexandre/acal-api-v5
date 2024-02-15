package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.domain.dto.pagination.DefaultFilter
import br.org.acal.apicore.domain.dto.pagination.PageFilter
import org.springframework.data.domain.Page

interface CustomDataSource<T> {
    fun save(t: T): T
    fun delete(id: String)
    fun findAll(): List<T>
    fun findById(id: String): T?
    fun findByFilter(filter: DefaultFilter): List<T>

    fun paginateByFilter(filter: PageFilter): Page<T>
}