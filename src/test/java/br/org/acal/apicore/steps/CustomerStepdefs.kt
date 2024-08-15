package br.org.acal.apicore.steps

import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.When
import stub.customerStub

class CustomerStepdefs: RestStepDefs() {

    @When("database has customer id {string}")
    fun databaseHasCustomerId(id: String) {
        val customer = customerStub.copy(id = id)
        customerRepository.save(customer.toDocument())
    }

}