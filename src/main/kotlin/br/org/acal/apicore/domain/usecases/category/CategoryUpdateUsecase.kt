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
class  CategoryUpdateUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<Category, Category>, Sl4jLogger() {

    @Transactional(propagation = REQUIRES_NEW)
    override fun execute(input: Category): Category = valid(input).let {
        dataSource.save(input)
    }

    private fun valid(actual: Category){
        dataSource.findByNameAndType(actual.name.trim(), actual.type)
            ?.let {

                if(!isEquals(it, actual) && isDuplication(it, actual)){
                    throw InvalidUsecaseException("This actions can't be realized because conflict with previous data")
                }

                it
            } ?: throw InvalidUsecaseException("Category can't be updated because does not exists.")
    }
    private fun isDuplication(previous: Category, actual: Category): Boolean =
        previous.name == actual.name && previous.type == actual.type

    private fun isEquals(previous: Category, actual: Category): Boolean =
        previous.id == actual.id

}
