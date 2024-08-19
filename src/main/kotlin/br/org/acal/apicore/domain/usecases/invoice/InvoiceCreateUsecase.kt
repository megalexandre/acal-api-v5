package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.dto.pagination.invoice.InvoiceFilter
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceCreateUsecase(
    private val dataSource: InvoiceDataSource
) : Usecase<Invoice, Invoice>, Sl4jLogger() {

    @Transactional
    override fun execute(input: Invoice) =
        valid(input).let {
            dataSource.save(input)
        }

    private fun valid( invoice: Invoice) {
        val filter = InvoiceFilter(
            linkId = invoice.linkId,
            reference = invoice.reference
        )

        dataSource.findByFilter(filter)
            .takeIf { it.isNotEmpty() }
            ?.let {
                throw InvalidUsecaseException("Already exists a invoice with this reference: ${invoice.reference}")
            }
    }

}
