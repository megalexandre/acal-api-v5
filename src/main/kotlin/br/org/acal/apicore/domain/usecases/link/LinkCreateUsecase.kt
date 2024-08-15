package br.org.acal.apicore.domain.usecases.link

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.LinkCreate
import br.org.acal.apicore.domain.usecases.address.AddressGetUsecase
import br.org.acal.apicore.domain.usecases.category.CategoryGetUsecase
import br.org.acal.apicore.domain.usecases.customer.CustomerGetUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import io.azam.ulidj.ULID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class LinkCreateUsecase(
    private val dataSource: LinkDataSource,
    private val getAddress: AddressGetUsecase,
    private val getCustomer: CustomerGetUsecase,
    private val getCategory: CategoryGetUsecase,

) : Usecase<LinkCreate, Link>, Sl4jLogger() {

    @Transactional
    override fun execute(input: LinkCreate): Link =
        dataSource.save(Link(
            id = ULID.random(),
            address = getAddress.execute(input.addressId),
            customer = getCustomer.execute(input.customerId),
            category = getCategory.execute(input.categoryId),
            active = true,
            suspended = false,
        ))

}
