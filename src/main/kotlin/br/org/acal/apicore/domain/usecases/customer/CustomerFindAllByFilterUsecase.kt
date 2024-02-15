package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.dto.pagination.customer.CustomerFilter
import br.org.acal.apicore.domain.entity.Customer
import org.springframework.stereotype.Service


@Service
class CustomerFindAllByFilterUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<CustomerFilter, List<Customer>> {

    override fun execute(input: CustomerFilter): List<Customer> = dataSource.findByFilter(input)

}
