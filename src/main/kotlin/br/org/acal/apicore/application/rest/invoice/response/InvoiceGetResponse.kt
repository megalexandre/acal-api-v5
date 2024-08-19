package br.org.acal.apicore.application.rest.invoice.response

import br.org.acal.apicore.common.enums.Reason
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceDetail
import br.org.acal.apicore.domain.entity.Link
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class InvoiceGetResponse(
    val id: String,
    val reference: String,
    val invoiceNumber: String,
    val emission: LocalDateTime?,
    val dueDate: LocalDate,
    val link: LinkResponse,
    val details: List<InvoiceDetailResponse>,
) {
    companion object{
        fun of(invoice: Invoice, link: Link) =  InvoiceGetResponse(
            id = invoice.id,
            reference = invoice.reference.value,
            invoiceNumber = invoice.invoiceNumber.value,
            emission = invoice.emission,
            dueDate = invoice.dueDate,
            link = LinkResponse.of(link),
            details = invoice.invoiceDetails.map { InvoiceDetailResponse.of(it)  }
        )
    }
}

data class InvoiceDetailResponse(
    val reason: Reason,
    val value: BigDecimal,
    val dataPaid: LocalDateTime?,
) {
    companion object{
        fun of(invoiceDetail: InvoiceDetail) = InvoiceDetailResponse(
            reason = invoiceDetail.reason,
            value = invoiceDetail.value,
            dataPaid = invoiceDetail.dataPaid,
        )
    }
}

data class LinkResponse(
    val customer: CustomerInvoiceResponse,
    val address: AddressInvoiceResponse,
    val category: CategoryInvoiceResponse,
){
    companion object{
        fun of(link: Link) = LinkResponse(
            customer = CustomerInvoiceResponse.of(link.customer),
            address = AddressInvoiceResponse.of(link.address),
            category = CategoryInvoiceResponse.of(link.category)
        )
    }
}

data class CustomerInvoiceResponse (
    val name: String,
    val documentNumber: String,
    var birthDay: LocalDate? = null,
) {
    companion object{
        fun of(customer: Customer) = CustomerInvoiceResponse(
            name = customer.name,
            documentNumber = customer.documentNumber.number,
            birthDay = customer.birthDay
        )
    }
}

data class AddressInvoiceResponse(
    val area: String,
    val number: String,
    val letter: String,
    val hasHydrometer: Boolean,
){
    companion object{
        fun of(address: Address) = AddressInvoiceResponse(
            area = address.area.name,
            number = address.number,
            letter = address.letter,
            hasHydrometer = address.hasHydrometer,
        )
    }
}

data class CategoryInvoiceResponse(
    val name: String,
    val type: String,
    val total: BigDecimal,
){
    companion object {
        fun of(category: Category) = CategoryInvoiceResponse(
            name = category.name,
            type = category.type.name,
            total = category.total,
        )
    }
}

data class CategoryValuesResponse(
    val name: String,
    val value: BigDecimal,
){
    companion object {
        fun of(categoryValues: CategoryValues) = CategoryValuesResponse(
            name = categoryValues.name,
            value = categoryValues.value,
        )

    }
}
