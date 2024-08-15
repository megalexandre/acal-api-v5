package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.customer.request.CustomerCreateRequest
import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.And
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue
import stub.customerStub

class CustomerStepdefs: RestStepDefs() {

    @When("database has customer id {string}")
    fun databaseHasCustomerId(id: String) {
        val customer = customerStub.copy(id = id)
        customerRepository.save(customer.toDocument())
    }

    @When("a customer is send by post")
    fun aCustomerIsSendByPost(jsonData: String) {
        val request = gson.fromJson(jsonData, CustomerCreateRequest::class.java)
        stepShared.response = executePost("customer", gson.toJson(request))
    }

    @And("the database needs to have an customer with name {string} and document {string}")
    fun theDatabaseNeedsToHaveAnCustomerWithNameAndDocument(name: String, document: String) {
        assertTrue(customerRepository.findAll().any { it.name == name && it.documentNumber == document })
    }

    @When("a {string} is send by post")
    fun aIsSendByPost(route: String, jsonData: String) {
        stepShared.response = executePost(route, jsonData)

    }
}