package stub

import br.org.acal.apicore.domain.entity.Invoice
import io.azam.ulidj.ULID.random
import java.time.LocalDate
import java.time.LocalDateTime

val invoiceStub = Invoice(
    id = random(),
    reference = referenceCurrentStub,
    invoiceNumber = invoiceNumberStub,
    emission = LocalDateTime.now(),
    dueDate = LocalDate.now().plusMonths(1),
    linkId= random(),
    invoiceDetails = listOf(invoiceDetailStub),
)

val invoiceNotPaidStub = Invoice(
    id = random(),
    reference = referenceCurrentStub,
    invoiceNumber = invoiceNumberStub,
    emission = LocalDateTime.now(),
    dueDate = LocalDate.now().plusMonths(1),
    linkId= random(),
    invoiceDetails = listOf(invoiceDetailNotPaidStub),
)

val invoicePaidStub = Invoice(
    id = random(),
    reference = referenceCurrentStub,
    invoiceNumber = invoiceNumberStub,
    emission = LocalDateTime.now(),
    dueDate = LocalDate.now().plusMonths(1),
    linkId = random(),
    invoiceDetails = listOf(invoiceDetailPaidStub),
)

val invoiceOverDueStub = Invoice(
    id = random(),
    reference = referenceCurrentStub,
    invoiceNumber = invoiceNumberStub,
    emission = LocalDateTime.now(),
    dueDate = LocalDate.now().minusMonths(1),
    linkId = random(),
    invoiceDetails = listOf(invoiceDetailPaidStub),
)
