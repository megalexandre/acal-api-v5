package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.info
import org.springframework.stereotype.Service


@Service
class AddressFindByIdUsecase(
    private val dataSource: AddressDataSource
) : Usecase<String, Address>, Sl4jLogger() {

    override fun execute(input: String): Address = dataSource.findById(input)
        ?: throw DataNotFoundException("does not exists address with id: $input")
        .also {
            logger.info { "get address by id $input was interrupted because does not exists address with id" }
        }

}
