package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceCreateUsecase(
    private val dataSource: InvoiceDataSource
) : Usecase<Invoice, Invoice>, Sl4jLogger() {

    @Transactional
    override fun execute(input: Invoice) = dataSource.save(input)

}
