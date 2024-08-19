package stub

import br.org.acal.apicore.application.rest.invoice.request.InvoiceDetailRequest
import br.org.acal.apicore.common.enums.Reason
import br.org.acal.apicore.domain.entity.InvoiceDetail
import java.math.BigDecimal.ONE
import java.time.LocalDateTime

val invoiceDetailStub = InvoiceDetail(
    reason = Reason.INVOICE,
    value = ONE,
    dataPaid = null
)

val invoiceDetailPaidStub = InvoiceDetail(
    reason = Reason.INVOICE,
    value = ONE,
    dataPaid = LocalDateTime.now()
)

val invoiceDetailNotPaidStub = InvoiceDetail(
    reason = Reason.INVOICE,
    value = ONE,
    dataPaid = null
)

val invoiceDetailRequestStub = InvoiceDetailRequest(
    reason = Reason.WATER,
    value =  ONE,
    dataPaid = null,
)
