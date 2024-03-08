package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.invoice.response.InvoiceGetResponse
import br.org.acal.apicore.domain.entity.Reference
import br.org.acal.apicore.resources.document.adapter.toDocument
import io.azam.ulidj.ULID.random
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import stub.invoiceStub
import stub.linkStub

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

    @Given("database has active link with invoice for reference {string}")
    fun databaseHasActiveLinkWithInvoiceForReference(referenceString: String) {
        val reference = Reference.of(referenceString)
        val linkId = random()
        val invoiceId = random()

        val linkStub = linkStub.copy(
            suspended = false,
            active = true,
        )

        val invoiceStub = invoiceStub.copy(
            id = invoiceId,
            linkId = linkId,
            reference = reference,
        )

        linkRepository.save(linkStub.toDocument())
        invoiceRepository.save(invoiceStub.toDocument())
    }

    @Given("database has two active link without invoice for reference {string}")
    fun databaseHasTwoActiveLinkWithoutInvoiceForReference(referenceString: String) {
        val linkStubOne = linkStub.copy(
            id = random(),
            suspended = false,
            active = true,
        )

        val linkStubTwo = linkStub.copy(
            id = random(),
            suspended = false,
            active = true,
        )

        linkRepository.save(linkStubOne.toDocument())
        linkRepository.save(linkStubTwo.toDocument())
    }

    @Given("database has inactive link with invoice for reference {string}")
    fun databaseHasInactiveLinkWithInvoiceForReference(referenceString: String) {
        val linkStubInactive = linkStub.copy(
            active = false,
        )

        linkRepository.save(linkStubInactive.toDocument())
    }

    @When("I find invoice proposal by reference {string}")
    fun iFindInvoiceProposalByReference(reference: String) {
        stepShared.response = executeGet("invoice/proposal/$reference")
    }

    @And("the response should has {int} invoices")
    fun theResponseShouldHasInvoices(size: Int) {
        val invoiceList = gson.fromJson(
            stepShared.response?.body?.asString(),
            Array<InvoiceGetResponse>::class.java
        ).toList()

        assertEquals(size, invoiceList.size)

    }
}
