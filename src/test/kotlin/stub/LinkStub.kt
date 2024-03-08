package stub

import br.org.acal.apicore.domain.entity.Link
import io.azam.ulidj.ULID.random

val linkStub = Link(
    id = random(),
    customer = customerStub,
    address = addressStub,
    category = categoryStub,
    suspended = true,
    active = true,
)
