package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.dto.pagination.customer.CustomerPageFilter
import br.org.acal.apicore.domain.entity.Customer
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class CustomerPaginateUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<CustomerPageFilter, Page<Customer>> {

    override fun execute(input: CustomerPageFilter): Page<Customer> = dataSource.paginateByFilter(input)

}
