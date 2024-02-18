package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.CustomerDataSource
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import br.org.acal.apicore.infrastructure.info
import org.springframework.stereotype.Service

@Service
class InvoiceCreateUsecase(
    private val dataSource: InvoiceDataSource
) : Usecase<Invoice, Invoice>, Sl4jLogger() {

    override fun execute(input: Invoice): Invoice = dataSource.save(input)


}
