package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.DataNotFoundException
import br.org.acal.apicore.infrastructure.info
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class  InvoiceGetUsecase(
    private val dataSource: InvoiceDataSource
) : Usecase<String, Invoice>, Sl4jLogger() {

    @Transactional
    override fun execute(input: String): Invoice =
        dataSource.findById(input) ?: throw DataNotFoundException("does not exists category with id: $input")
        .also {
            logger.info { "get invoice by id $input was interrupted because does not exists invoice with id" }
        }

}
