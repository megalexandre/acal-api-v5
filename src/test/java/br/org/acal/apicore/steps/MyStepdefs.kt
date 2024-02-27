package br.org.acal.apicore.steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.response.Response
import org.apache.http.HttpStatus.SC_OK
import org.junit.jupiter.api.Assertions.assertEquals

class MyStepdefs: SpringIT() {

    var response: Response? = null

    @When("I send a GET request to {string}")
    fun iSendAGETRequestTo(url: String) {
        response = executeGet(url)
    }

    @Then("the response status code should be {int}")
    fun theResponseStatusCodeShouldBe(status: Int) {
        assertEquals(SC_OK,response?.statusCode)
    }

}