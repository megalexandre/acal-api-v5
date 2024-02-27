package br.org.acal.apicore.steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class MyStepdefs: SpringIT() {

    @When("I send a GET request to {string}")
    fun iSendAGETRequestTo(url: String) {

    }

    @Then("the response status code should be {int}")
    fun theResponseStatusCodeShouldBe(status: Int) {

    }

}