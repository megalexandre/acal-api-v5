package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.entity.Category
import org.springframework.stereotype.Service


@Service
class CategoryFindAllUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<Unit, List<Category>> {

    override fun execute(input: Unit): List<Category> = dataSource.findAll()

}
