package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service

@Service
class CustomerDeleteUsecase(
    private val dataSource: CustomerDataSource
): Usecase<String, Unit>, Sl4jLogger()  {

    override fun execute(input: String){
        dataSource.delete(input)
    }

}