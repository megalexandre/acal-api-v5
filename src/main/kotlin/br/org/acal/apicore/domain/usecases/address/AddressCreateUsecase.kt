package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.dto.pagination.address.AddressFilter
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AddressCreateUsecase(
    private val dataSource: AddressDataSource
) : Usecase<Address, Address>, Sl4jLogger() {

    @Transactional
    override fun execute(input: Address): Address = valid(input).let {
        dataSource.save(input)
    }

    private fun valid(address: Address) {
        dataSource.findByFilter(AddressFilter(areaId = address.area.id, number = address.number))
            .takeIf { it.isNotEmpty() }
            ?.let {
                throw InvalidUsecaseException(
                    """Already exists an address with this number: ${address.number} and area ${address.area.name}"""
                )
            }


    }

}
