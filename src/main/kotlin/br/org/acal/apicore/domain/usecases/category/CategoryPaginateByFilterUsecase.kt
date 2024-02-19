package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.dto.pagination.category.CategoryPageFilter
import br.org.acal.apicore.domain.entity.Category
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class CategoryPaginateByFilterUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<CategoryPageFilter, Page<Category>> {

    override fun execute(input: CategoryPageFilter): Page<Category> = dataSource.paginateByFilter(input)

}
