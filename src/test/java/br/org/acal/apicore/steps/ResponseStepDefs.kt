package br.org.acal.apicore.steps

import io.cucumber.java.en.Then
import org.junit.jupiter.api.Assertions.assertEquals

class ResponseStepDefs: RestStepDefs() {

    @Then("o status de resposta deve ser {int}")
    fun responseStateShouldBe(status: Int) {
        assertEquals(status, stepShared.response?.statusCode)
    }

    @Then("the status response should be {int}")
    fun theStatusResponseShouldBe(status: Int) {
        assertEquals(status, stepShared.response?.statusCode)
    }
}