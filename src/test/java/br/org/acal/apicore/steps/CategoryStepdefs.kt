package br.org.acal.apicore.steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.assertEquals

class CategoryStepdefs: StepDefs() {

    var response: Response? = null

    @When("I send a POST request to {string}")
    fun iSendAPOSTRequestTo(url: String, body: String)  {
        response = executePost(url, body)
    }

    @When("I send a GET request to {string}")
    fun iSendAGETRequestTo(url: String) {
        response = executeGet(url)
    }

    @Then("the response status code should be {int}")
    fun theResponseStatusCodeShouldBe(status: Int) {
        assertEquals(status, response?.statusCode)
    }


}