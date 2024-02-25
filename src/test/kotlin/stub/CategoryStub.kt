package stub

import br.org.acal.apicore.application.rest.category.request.CategoryCreateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryCreateValuesRequest
import br.org.acal.apicore.common.enums.CategoryType
import java.math.BigDecimal

val categoryCreateStub = CategoryCreateRequest(
    name = "Sócio Efetivo",
    type = CategoryType.EFFECTIVE,
    values = listOf(
        CategoryCreateValuesRequest(
            name = "Water",
            value = BigDecimal.TEN
        ),
        CategoryCreateValuesRequest(
            name = "partnership",
            value = BigDecimal.ONE
        )
    ),
)

