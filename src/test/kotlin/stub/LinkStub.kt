package stub

import br.org.acal.apicore.application.components.validator.invoiceNumber.InvoiceNumberValidator
import br.org.acal.apicore.application.rest.invoice.request.InvoiceDetailRequest
import br.org.acal.apicore.domain.entity.Link
import io.azam.ulidj.ULID.random
import java.time.LocalDate
import java.time.LocalDateTime

val linkStub = Link(
    id = random(),
    customer = customerStub,
    address = addressStub,
    category = categoryStub,
    suspended = true,
    active = true,
)





