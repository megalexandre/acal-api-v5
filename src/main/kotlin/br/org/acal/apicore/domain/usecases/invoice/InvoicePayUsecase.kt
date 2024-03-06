package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import java.time.LocalDateTime.now
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoicePayUsecase(
    private val dataSource: InvoiceDataSource,
    private val get: InvoiceGetUsecase
) : Usecase<String, Boolean>, Sl4jLogger() {

    @Transactional
    override fun execute(input: String): Boolean {

        get.execute(input).let  {

            if(it.isPayed){
                throw InvalidUsecaseException("this invoice $input already is paid")
            }

            dataSource.save(
                it.copy(
                    invoiceDetails = it.invoiceDetails.map { detail ->
                        detail.copy(dataPaid = now())
                    }
                )
            )
        }

        return true
    }

}
