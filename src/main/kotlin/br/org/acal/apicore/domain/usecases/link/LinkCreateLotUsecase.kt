package br.org.acal.apicore.domain.usecases.link

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.entity.LinkCreate
import br.org.acal.apicore.domain.usecases.address.AddressGetUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryGetUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerGetUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service


@Service
class LinkCreateLotUsecase(
    private val create: LinkCreateUsecase,
    private val customerGetUsecase: CustomerGetUsecase,
    private val addressGetUsecase: AddressGetUsecase,
    private val categoryGetUsecase: CategoryGetUsecase,
) : Usecase<List<LinkCreate>, Unit>, Sl4jLogger() {

    override fun execute(input: List<LinkCreate>) {

    }

}

