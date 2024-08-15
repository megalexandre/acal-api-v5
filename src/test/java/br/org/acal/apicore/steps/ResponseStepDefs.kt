package br.org.acal.apicore.steps

import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue


class ResponseStepDefs: RestStepDefs() {

    @Then("the status response should be {int}")
    fun theStatusResponseShouldBe(status: Int) {
        assertEquals(status, stepShared.response?.statusCode)
    }

    @And("the response content should has {int} elements")
    fun theResponseContentShouldHasElements(arg0: Int) {
       stepShared.response?.asString()
    }

    @And("and response has {string} equal to {string}")
    fun andResponseHasEqualTo(field: String, message: String) {
        val response = stepShared.response?.asString() ?: ""

        assertTrue(response.contains(message), "response should contain $field as $message but wat $response ")
    }
}