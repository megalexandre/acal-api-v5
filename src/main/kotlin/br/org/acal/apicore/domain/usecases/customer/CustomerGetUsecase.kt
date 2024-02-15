package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service


@Service
class CustomerGetUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<String, Customer> {

    override fun execute(input: String): Customer =
        dataSource.findById(input) ?: throw InvalidUsecaseException("does not exists an customer with id: $input")

}
