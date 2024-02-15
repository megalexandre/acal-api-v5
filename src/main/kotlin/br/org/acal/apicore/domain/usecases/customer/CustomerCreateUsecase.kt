package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import br.org.acal.apicore.infrastructure.info
import org.springframework.stereotype.Service


@Service
class CustomerCreateUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<Customer, Customer>, Sl4jLogger() {

    override fun execute(input: Customer): Customer =
        valid(input).let {
            dataSource.save(input)
        }

    private fun valid(customer: Customer) {
        dataSource.findByDocument(customer.documentNumber)?.let {
            logger.info { "Already exists a customer with document ${customer.documentNumber.number}" }

            throw InvalidUsecaseException(
                "Already exists a customer with document ${customer.documentNumber.number}"
            )
        }
    }

}
