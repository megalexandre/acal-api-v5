package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.category.request.CategoryCreateValuesRequest
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.resources.document.adapter.toDocument
import br.org.acal.apicore.resources.document.adapter.toEntity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import stub.categoryCreateStub
import stub.categoryStub

class CategoryStepDefs: RestStepDefs() {

    @Given("a category with name {string} and type {string} is send by post")
    fun aCategoryWithNameAndTypeIsSendByPost(name: String, type: String) {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                name = name,
                type = CategoryType.valueOf(type),
            )
        ))
    }

    @Given("a category with water value of {int} and category value of {int} is sent")
    fun aCategoryWithWaterValueOfAndCategoryValueOfIsSent(waterValue: Int, categoryValue: Int) {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                values = listOf(
                    CategoryCreateValuesRequest(
                        name = "Water",
                        value = BigDecimal(waterValue)
                    ),
                    CategoryCreateValuesRequest(
                        name = "partnership",
                        value = BigDecimal(categoryValue)
                    )
                )
            )
        ))
    }

    @Then("there must be a saved category with a name value of {int}")
    fun thereMustBeASavedCategoryWithANameValueOf(totalValue: Int) {
        val category = categoryRepository.findById(stepShared.get("id")).get()
        assertEquals(totalValue, category.toEntity().total.intValueExact())
    }

    @Given("the base has three categories one be type")
    fun theBaseHasThreeCategoriesOneBeType() {
        categoryRepository.saveAll(
            CategoryType.entries.map {
                categoryStub.copy(type = it).toDocument()
            }
        )
    }
    @When("i query paginate")
    fun iQueryPaginate() {
        stepShared.response = executeGet("category/paginate")
    }
}