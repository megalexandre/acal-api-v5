package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class  CategoryCreateUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<Category, Category>, Sl4jLogger() {

    @Transactional
    override fun execute(input: Category): Category =
        dataSource.save(input)

}
