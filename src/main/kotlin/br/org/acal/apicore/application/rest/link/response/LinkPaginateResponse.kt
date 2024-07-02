package br.org.acal.apicore.application.rest.link.response

import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.PhoneNumber
import java.math.BigDecimal
import java.time.LocalDate
import org.springframework.data.domain.Page

data class LinkPaginateResponse (
    val id: String,
    val customer: LinkCustomerResponse,
    val address: LinkAddressResponse,
    val category: LinkCategoryResponse,
    val suspended: Boolean,
    val active: Boolean,

    ) {
    constructor(link: Link): this(
        id = link.id,
        customer = LinkCustomerResponse.of(link.customer),
        address = LinkAddressResponse.of(link.address),
        category = LinkCategoryResponse.of(link.category),
        suspended = link.suspended,
        active = link.active,
    )
}

data class LinkCategoryResponse(
    val id: String,
    val name: String,
    val type: CategoryType,
    val total: BigDecimal,
){
    companion object{
        fun of(category: Category)= with(category){
            LinkCategoryResponse(
                id = id,
                name = name,
                type = type,
                total = total
            )
        }
    }
}

data class LinkAddressResponse(
    val id: String,
    val area: LinkAddressAreaResponse,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
    val active: Boolean,
){
    companion object{
        fun of(address: Address) = with(address){
            LinkAddressResponse(
                id = id,
                area = LinkAddressAreaResponse.of(area),
                number = number,
                letter = letter,
                hasHydrometer = hasHydrometer,
                active = active,
            )
        }
    }
}

data class LinkAddressAreaResponse(
    val id: String,
    val name: String
){

    companion object{
        fun of(area: Area) = with(area){
            LinkAddressAreaResponse(id = id, name = name)
        }
    }
}


data class LinkCustomerResponse(
    val id: String,
    val name: String,
    val documentNumber: String,
    var birthDay: LocalDate? = null,
    val phoneNumbers: List<PhoneNumber>? = null,
    val active: Boolean,
) {
    companion object{
        fun of(customer: Customer) = LinkCustomerResponse(
            id = customer.id,
            name = customer.name,
            documentNumber = customer.documentNumber.number,
            birthDay = customer.birthDay,
            phoneNumbers =  customer.phoneNumbers,
            active = customer.active,
        )
    }
}

fun Page<Link>.toLinkPaginateResponse(): Page<LinkPaginateResponse> = this.map { LinkPaginateResponse(it)  }