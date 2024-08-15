package stub

import br.org.acal.apicore.domain.entity.Customer
import br.org.acal.apicore.domain.entity.DocumentNumber
import br.org.acal.apicore.resources.document.adapter.toDocument
import io.azam.ulidj.ULID
import java.time.LocalDate

val customerStub = Customer(
    id = ULID.random(),
    name = "Jonh Doe",
    documentNumber =  DocumentNumber("29695487041"),
    birthDay = LocalDate.now(),
    phoneNumbers = null,
    active = true,
)

val customerDocumentStub = customerStub.toDocument()