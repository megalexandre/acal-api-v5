package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import br.org.acal.apicore.infrastructure.info
import br.org.acal.apicore.infrastructure.kotlin.extensions.alsoTrue
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceCreateLotUsecase(
    private val dataSource: InvoiceDataSource
): Usecase<List<Invoice>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<Invoice>) {

        input.any { !valid(it)  }.alsoTrue {
            throw InvalidUsecaseException("at least a invoice is not save")
        }

        dataSource.saveAll(input)
    }

    private fun valid(invoice: Invoice): Boolean =
        !(dataSource.existsInvoiceForReferenceAndLink(invoice)
          .alsoTrue {
            logger.info {"this invoice $invoice is not valid"  }
        })

}
