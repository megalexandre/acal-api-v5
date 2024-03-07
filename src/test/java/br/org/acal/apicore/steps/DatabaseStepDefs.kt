package br.org.acal.apicore.steps

import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import stub.invoiceStub
import stub.linkStub

class DatabaseStepDefs: RestStepDefs() {

    @When("database is clean")
    fun databaseIsClean() {
        super.resetDatabase()
    }

    @Given("database has invoice with id {string} and linkId {string}")
    fun databaseHasInvoiceWithIdAndLinkId(invoiceId: String, linkId: String) {
        super.resetDatabase()

        linkRepository.save(
            linkStub.copy(
                id = linkId
            ).toDocument()
        )

        invoiceRepository.save(
            invoiceStub.copy(
                id = invoiceId,
                linkId = linkId
            ).toDocument()
        )
    }

}