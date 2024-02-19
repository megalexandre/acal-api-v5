package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class  CategoryGetUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<String, Category>, Sl4jLogger() {

    @Transactional
    override fun execute(input: String): Category =
        dataSource.findById(input) ?: throw InvalidUsecaseException("does not exists category with id: $input")

}
