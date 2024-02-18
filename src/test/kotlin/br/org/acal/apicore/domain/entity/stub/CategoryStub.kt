package br.org.acal.apicore.domain.entity.stub

import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import io.azam.ulidj.ULID
import java.math.BigDecimal


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