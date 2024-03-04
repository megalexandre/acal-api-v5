package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.category.request.CategoryCreateValuesRequest
import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.resources.document.adapter.toEntity
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.response.Response
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import stub.categoryCreateStub

class CategoryStepdefs: StepDefs() {

    var response: Response? = null

    @When("o banco de dados está vazio")
    fun oBancoDeDadosEstaVazio() {
        super.resetDatabase()
    }

    @And("uma categoria como nome {string} e tipo {string} é salva")
    fun umaCategoriaComoNomeETipoESalva(name: String, type: String) {
        response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                name = name,
                type = CategoryType.valueOf(type),
            )
        ))
    }

    @Then("o status de de resposta deve ser {int}")
    fun oStatusDeDeRespostaDeveSer(status: Int) {
        assertEquals(status, response?.statusCode)
    }

    @And("deve haver uma categoria salva com nome {string} e tipo {string}")
    fun deveHaverUmaCategoriaSalvaComNomeETipo(name: String, type: String) {
        categoryRepository.findAll().first().let {
            assertEquals(name, it.name)
            assertEquals(type, it.type.name)
        }
    }

    @When("uma categoria com valor de agua de {int} e valor de categoria igual a {int} é salva")
    fun umaCategoriaComValorDeAguaDeEValorDeCategoriaIgualA(waterValue: Int, categoryValue: Int) {
        response = executePost("category", gson.toJson(
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

    @Then("deve haver uma categoria salva com nome valor de {int}")
    fun deveHaverUmaCategoriaSalvaComNomeValorDe(value: Int) {
        categoryRepository.findAll().first().let {
            assertEquals(BigDecimal(value).intValueExact(), it.toEntity().total.intValueExact())
        }
    }

    @When("uma categoria sem valores é enviada")
    fun umaCategoriaSemValoresEEnviada() {
        response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                values = emptyList()
            )
        ))
    }

    @And("o banco de dados deve estar vazio")
    fun oBancoDeDadosDeveEstarVazio() {
        assertEquals(0, categoryRepository.findAll().size)
    }

    @When("uma categoria com valores negativos é enviada")
    fun umaCategoriaComValoresNegativosEEnviada() {
        response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                values = listOf(
                    CategoryCreateValuesRequest(
                        name = "Water",
                        value = BigDecimal(-10)
                    ),
                    CategoryCreateValuesRequest(
                        name = "partnership",
                        value = BigDecimal(-10)
                    )
                )
            )
        ))
    }
}