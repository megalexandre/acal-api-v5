package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.Reference
import br.org.acal.apicore.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service

@Service
class InvoiceProposalUsecase(
    private val dataSource: InvoiceDataSource
): Usecase<Reference, List<Invoice>>, Sl4jLogger() {
    override fun execute(input: Reference) =
        dataSource.findProposal(input)

}
