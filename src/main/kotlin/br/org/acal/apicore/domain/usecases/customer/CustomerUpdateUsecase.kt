package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service

@Service
class CustomerUpdateUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<Customer, Customer>, Sl4jLogger() {

    override fun execute(input: Customer): Customer =
        valid(input).let {
            dataSource.save(input)
        }

    private fun valid(customer: Customer) {
    }

}
