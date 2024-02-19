package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceCreateLotUsecase(
    private val dataSource: InvoiceDataSource
) : Usecase<List<Invoice>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<Invoice>) {
        dataSource.save(input)
    }


}
