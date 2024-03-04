package br.org.acal.apicore.stub

import br.org.acal.apicore.application.rest.category.request.CategoryCreateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryCreateValuesRequest
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import io.azam.ulidj.ULID
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

val categoryStub = Category(
    id = ULID.random(),
    name = "Residente",
    type = CategoryType.EFFECTIVE,
    values =
    listOf(
        CategoryValues(
            name = "WATER",
            value = BigDecimal.TEN
        ),
        CategoryValues(
            name = "PARTNERSHIP",
            value = BigDecimal.TEN
        )
    ),
)