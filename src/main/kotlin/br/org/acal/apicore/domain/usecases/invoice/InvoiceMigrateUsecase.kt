package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.InvoiceMigrate
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceMigrateUsecase(
    private val create: InvoiceCreateUsecase
) : Usecase<List<InvoiceMigrate>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<InvoiceMigrate>) {
        create.execute(input)
    }


}
