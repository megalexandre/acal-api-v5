package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AddressCreateUsecase(
    private val dataSource: AddressDataSource
) : Usecase<Address, Address>, Sl4jLogger() {

    @Transactional
    override fun execute(input: Address): Address =
        dataSource.save(input)



}
