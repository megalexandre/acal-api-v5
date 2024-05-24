package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service


@Service
class  CategoryDeleteUsecase(
    private val dataSource: CategoryDataSource,
) : Usecase<String, Unit>, Sl4jLogger() {

    override fun execute(input: String) {
        dataSource.delete(input)
    }


}
