package br.org.acal.apicore.domain.usecases.address

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AddressCreateLotUsecase(
    private val create: AddressCreateUsecase
) : Usecase<List<Address>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<Address>){
        input.forEach { create.execute(it) }
    }

}
