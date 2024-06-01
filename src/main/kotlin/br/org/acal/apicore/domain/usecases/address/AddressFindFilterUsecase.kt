package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.dto.pagination.address.AddressFilter
import br.org.acal.apicore.domain.entity.Address
import org.springframework.stereotype.Service


@Service
class AddressFindFilterUsecase(
    private val dataSource: AddressDataSource
) : Usecase<AddressFilter, List<Address>> {

    override fun execute(input: AddressFilter): List<Address> = dataSource.findByFilter(input)

}
