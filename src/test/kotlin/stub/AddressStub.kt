package stub

import br.org.acal.apicore.application.rest.address.request.AddressCreateRequest
import br.org.acal.apicore.application.rest.address.request.AreaRequest
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area
import io.azam.ulidj.ULID.random

val areaStub = Area(
    id = random(),
    name = "AVENIDA"
)

val addressStub = Address(
    id = random(),
    area = areaStub,
    number = "1",
    letter = "A",
    hasHydrometer = true,
    active = true,
)

val addressCreateRequest = AddressCreateRequest(
    number = "1",
    letter =  "A",
    hasHydrometer = true,
    area = AreaRequest(id = random(), name = "area name")
)