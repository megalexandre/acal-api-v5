package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.info
import org.springframework.stereotype.Service


@Service
class  CategoryGetUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<String, Category>, Sl4jLogger() {

    override fun execute(input: String): Category =
        dataSource.findById(input) ?: throw DataNotFoundException("does not exists category with id: $input")
            .also {
                logger.info { "get category by id $input was interrupted because does not exists category with id" }
            }


}
