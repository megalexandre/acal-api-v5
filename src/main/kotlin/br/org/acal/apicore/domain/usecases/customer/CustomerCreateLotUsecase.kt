package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CustomerCreateLotUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<List<Customer>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<Customer>) {
        dataSource.saveAll(input)
    }

}
