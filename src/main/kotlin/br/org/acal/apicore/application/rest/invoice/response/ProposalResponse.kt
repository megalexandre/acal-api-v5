package br.org.acal.apicore.application.rest.invoice.response

import br.org.acal.apicore.common.enums.Reason
import br.org.acal.apicore.common.util.sum
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.Proposal
import java.math.BigDecimal
import java.time.LocalDateTime


data class ProposalGroupResponse(
    val name: String,
    val links: List<ProposalResponse>,
    val total: BigDecimal = links.map { it.total }.sum(),
    val quantity: Int = links.size,
) {
    companion object {
        fun of(proposals: List<Proposal>): List<ProposalGroupResponse> =
            proposals
                .map { it.link.address.area.name }
                .distinct().sortedBy { it }
                .map { name ->
                    ProposalGroupResponse(
                        name = name,
                        links = proposals
                            .filter { name == it.link.address.area.name  }
                            .map { proposal ->
                                ProposalResponse(
                                    reference = proposal.reference.value,
                                    emission = proposal.emission,
                                    link = LinkProposalResponse.of(proposal.link),
                                    invoiceDetails = proposal.invoiceDetails.map{ InvoiceDetailProposalDetail(
                                        reason = it.reason,
                                        value = it.value,
                                        dataPaid = null,
                                    )} ,
                                )
                            }
                    )
                }
    }


}

data class ProposalResponse(
    val reference: String,
    val emission: LocalDateTime,
    val link: LinkProposalResponse,
    val invoiceDetails: List<InvoiceDetailProposalDetail>
){
    val total = invoiceDetails.map { it.value }.sum()
}

data class InvoiceDetailProposalDetail(
    val reason: Reason,
    val value: BigDecimal,
    val dataPaid: LocalDateTime?,
)
data class LinkProposalResponse(
    val customer: CustomerProposalInvoiceResponse,
    val address: AddressProposalResponse,
    val category: CategoryProposalResponse,
){
    companion object{
        fun of(link: Link) = LinkProposalResponse(
            customer = CustomerProposalInvoiceResponse.of(link.customer),
            address = AddressProposalResponse.of(link.address),
            category = CategoryProposalResponse.of(link.category)
        )
    }
}
data class CustomerProposalInvoiceResponse (
    val name: String,
    val documentNumber: String,
) {
    companion object{
        fun of(customer: Customer) = CustomerProposalInvoiceResponse(
            name = customer.name,
            documentNumber = customer.documentNumber.number,
        )
    }
}

data class AddressProposalResponse(
    val area: String,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
){
    companion object{
        fun of(address: Address) = AddressProposalResponse(
            area = address.area.name,
            number = address.number,
            letter = address.letter,
            hasHydrometer = address.hasHydrometer,
        )
    }
}

data class CategoryProposalResponse(
    val name: String,
    val type: String,
    val total: BigDecimal,
){
    companion object {
        fun of(category: Category) = CategoryProposalResponse(
            name = category.name,
            type = category.type.name,
            total = category.total,
        )
    }
}