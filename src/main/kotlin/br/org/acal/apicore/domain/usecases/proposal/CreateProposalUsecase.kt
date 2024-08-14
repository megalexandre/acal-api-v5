package br.org.acal.apicore.domain.usecases.proposal

import br.org.acal.apicore.common.enums.Reason
import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.datasource.LinkDataSource
import br.org.acal.apicore.domain.entity.InvoiceDetail
import br.org.acal.apicore.domain.entity.Proposal
import br.org.acal.apicore.domain.entity.Reference
import br.org.acal.apicore.infrastructure.Sl4jLogger
import java.time.LocalDateTime.now
import org.springframework.stereotype.Service

@Service
class CreateProposalUsecase(
    private val linkDataSource: LinkDataSource,
): Usecase<Reference, List<Proposal?>>, Sl4jLogger() {
    override fun execute(input: Reference): List<Proposal> =
        linkDataSource.findFindAllWithoutInvoiceForReferenceUsecase(input).map { link ->
            Proposal(
                reference = input,
                emission = now(),
                link = link,
                invoiceDetails = link.category.values.map { category ->
                    InvoiceDetail(
                        reason = Reason.get(category.name) ?: throw RuntimeException("this category has no reason $category"),
                        value = category.value,
                        dataPaid = null,
                    )
                },
            )
        }


}
