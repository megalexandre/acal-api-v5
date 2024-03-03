package br.org.acal.apicore.steps

import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.response.Response

class CategoryStepdefs: StepDefs() {

    var response: Response? = null

    @When("o banco de dados está vazio")
    fun oBancoDeDadosEstaVazio() {
        super.resetDatabase()
    }

    @And("uma categoria como nome {string} e tipo {string} é salva")
    fun umaCategoriaComoNomeETipoESalva(name: String, type: String) {
    }

    @Then("o status de de resposta deve ser {int}")
    fun oStatusDeDeRespostaDeveSer(status: Int) {

    }

    @And("deve haver uma categoria salva com nome {string} e tipo {string}")
    fun deveHaverUmaCategoriaSalvaComNomeETipo(name: String, type: String) {
    }
}