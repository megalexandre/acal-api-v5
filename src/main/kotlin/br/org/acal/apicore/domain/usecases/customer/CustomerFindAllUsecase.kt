package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.entity.Customer
import org.springframework.stereotype.Service


@Service
class CustomerFindAllUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<Unit, List<Customer>> {

    override fun execute(input: Unit): List<Customer> = dataSource.findAll()

}
