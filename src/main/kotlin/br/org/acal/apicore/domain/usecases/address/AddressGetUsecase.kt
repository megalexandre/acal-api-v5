package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.AddressDataSource
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AddressGetUsecase(
    private val dataSource: AddressDataSource
) : Usecase<String, Address>, Sl4jLogger() {

    @Transactional
    override fun execute(input: String): Address =
        dataSource.findById(input) ?: throw InvalidUsecaseException("does not exists address with id: $input")

}
