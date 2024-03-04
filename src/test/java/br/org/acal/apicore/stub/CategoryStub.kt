package br.org.acal.apicore.stub

import br.org.acal.apicore.application.rest.category.request.CategoryCreateRequest
import br.org.acal.apicore.application.rest.category.request.CategoryCreateValuesRequest
import br.org.acal.apicore.common.enums.CategoryType.EFFECTIVE
import br.org.acal.apicore.common.enums.CategoryType.FOUNDING
import br.org.acal.apicore.common.enums.CategoryType.TEMPORARY
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.domain.entity.CategoryValues
import br.org.acal.apicore.resources.document.CategoryDocument
import br.org.acal.apicore.resources.document.CategoryValuesDocument
import io.azam.ulidj.ULID.random
import java.math.BigDecimal
import java.math.BigDecimal.TEN

val categoryCreateStub = CategoryCreateRequest(
    name = "Sócio Efetivo",
    type = EFFECTIVE,
    values = listOf(
        CategoryCreateValuesRequest(
            name = "Water",
            value = TEN
        ),
        CategoryCreateValuesRequest(
            name = "partnership",
            value = BigDecimal.ONE
        )
    ),
)

val categoryStub = Category(
    id = random(),
    name = "Residente",
    type = EFFECTIVE,
    values =
    listOf(
        CategoryValues(
            name = "WATER",
            value = TEN
        ),
        CategoryValues(
            name = "PARTNERSHIP",
            value = TEN
        )
    ),
)

val categoryDocumentStub = CategoryDocument(
    id = random(),
    name = "Residente",
    type = EFFECTIVE,
    values =
    listOf(
        CategoryValuesDocument(
            name = "WATER",
            value = TEN
        ),
        CategoryValuesDocument(
            name = "PARTNERSHIP",
            value = TEN
        )
    ),
)

val listOfCategoryDocumentFromAllTypesStub =
    listOf(
        categoryDocumentStub.copy(
        id = random(),
        type = EFFECTIVE
    ),
        categoryDocumentStub.copy(
        id = random(),
        type = FOUNDING
    ),
        categoryDocumentStub.copy(
        id = random(),
        type = TEMPORARY
    )
)