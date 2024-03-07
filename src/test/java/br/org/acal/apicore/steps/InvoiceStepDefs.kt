package br.org.acal.apicore.steps

import io.cucumber.java.en.And
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertTrue

class InvoiceStepDefs: RestStepDefs() {

    @When("I find invoice by {string}")
    fun iFindInvoiceBy(id: String) {
        stepShared.response = executeGet("invoice/$id")
    }

    @And("the database must have an invoice with the id present in the response")
    fun theDatabaseMustHaveAnInvoiceWithTheIdPresentInTheResponse() {
        val invoice = invoiceRepository.findById(stepShared.get("id"))
        assertTrue(invoice.isPresent)
    }

}
