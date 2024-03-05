package stub

import br.org.acal.apicore.domain.entity.Reference
import java.time.LocalDate
import java.time.Year

val referenceCurrentStub = Reference(
    year = Year.of(LocalDate.now().year),
    month = LocalDate.now().month
)