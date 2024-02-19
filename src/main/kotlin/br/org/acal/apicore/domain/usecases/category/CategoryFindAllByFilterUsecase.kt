package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.dto.pagination.category.CategoryFilter
import br.org.acal.apicore.domain.entity.Category
import org.springframework.stereotype.Service


@Service
class CategoryFindAllByFilterUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<CategoryFilter, List<Category>> {

    override fun execute(input: CategoryFilter): List<Category> = dataSource.findByFilter(input)

}
