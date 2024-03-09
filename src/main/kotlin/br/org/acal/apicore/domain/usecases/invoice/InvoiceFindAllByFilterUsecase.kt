package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.dto.pagination.invoice.InvoiceFilter
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service


@Service
class  InvoiceFindAllByFilterUsecase(
    private val dataSource: InvoiceDataSource
) : Usecase<InvoiceFilter, List<Invoice>>, Sl4jLogger() {

    override fun execute(input: InvoiceFilter): List<Invoice> = dataSource.findByFilter(input)


}
