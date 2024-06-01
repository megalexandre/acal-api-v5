package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.dto.pagination.address.AddressPageFilter
import br.org.acal.apicore.domain.entity.Address
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class AddressPaginateUsecase(
    private val dataSource: AddressDataSource
) : Usecase<AddressPageFilter, Page<Address>> {

    override fun execute(input: AddressPageFilter): Page<Address> = dataSource.paginateByFilter(input)

}
