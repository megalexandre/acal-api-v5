package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.InvoiceMigrate
import br.org.acal.apicore.domain.entity.InvoiceNumber
import br.org.acal.apicore.domain.usecases.sequence.NextSequenceUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvoiceMigrateUsecase(
    private val create: InvoiceCreateUsecase,
    private val nextSequence: NextSequenceUsecase,
) : Usecase<List<InvoiceMigrate>, Unit>, Sl4jLogger() {

    @Transactional
    override fun execute(input: List<InvoiceMigrate>) {
        input.map {
            create.execute(
                Invoice(
                    id = it.id,
                    reference = it.reference,
                    invoiceNumber = InvoiceNumber(
                        year = it.reference.year,
                        month = it.reference.month,
                        number = nextSequence.execute(it.reference.value).toString()
                    ),
                    emission = it.emission,
                    dueDate = it.dueDate,
                    linkId = it.linkId,
                    invoiceDetails = it.invoiceDetails,
                )
            )
        }
    }
}
