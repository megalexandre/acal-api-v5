package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional


@Service
class  CategoryCreateUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<Category, Category>, Sl4jLogger() {

    @Transactional(propagation = REQUIRES_NEW)
    override fun execute(input: Category): Category = valid(input).let {
        dataSource.save(input)
    }

    private fun valid(category: Category) {
        dataSource.findByNameAndType(category.name.trim(), category.type)
            .takeIf { it?.isNotEmpty() == true }
            ?.let {
                throw InvalidUsecaseException("Already exists a category with this name: ${category.name} and type ${category.type}")
            }
    }

}
