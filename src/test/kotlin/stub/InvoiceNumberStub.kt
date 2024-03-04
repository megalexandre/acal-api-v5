package stub

import br.org.acal.apicore.domain.entity.InvoiceNumber
import java.time.LocalDateTime.now
import java.time.Month
import java.time.Year


val invoiceNumberStub = InvoiceNumber(

    year = Year.now(),
    month = Month.from(now()),
    number = "000000001"

)