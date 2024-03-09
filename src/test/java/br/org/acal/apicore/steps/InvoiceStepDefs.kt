package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.invoice.response.ProposalGroupResponse
import br.org.acal.apicore.common.util.sum
import br.org.acal.apicore.domain.entity.Reference
import br.org.acal.apicore.resources.document.adapter.toDocument
import io.azam.ulidj.ULID.random
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import stub.addressStub
import stub.areaStub
import stub.customerStub
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
            customer = customerStub.copy(name = "A | I have a invoice for reference :$referenceString"),
            id = linkId,
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

    @Given("database has two active in different areas link without invoice for reference {string}")
    fun databaseHasTwoActiveLinkWithoutInvoiceForReference(referenceString: String) {
        val linkStubOne = linkStub.copy(
            id = random(),
            customer = customerStub.copy(name = "C"),
            address = addressStub.copy(
                area = areaStub.copy(
                    name = "Avenida Fernando Daltro"
                ),
            ),
            suspended = false,
            active = true,
        )

        val linkStubTwo = linkStub.copy(
            id = random(),
            customer = customerStub.copy(name = "D"),
            address = addressStub.copy(
                area = areaStub.copy(
                    name = "Avenida Morro do chapeu"
                ),
            ),
            suspended = false,
            active = true,
        )

        linkRepository.save(linkStubOne.toDocument())
        linkRepository.save(linkStubTwo.toDocument())
    }

    @Given("database has inactive link with invoice for reference {string}")
    fun databaseHasInactiveLinkWithInvoiceForReference(referenceString: String) {
        val linkStubInactive = linkStub.copy(
            customer = customerStub.copy(name = "B"),
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
        val proposalGroupResponse = gson.fromJson(
            stepShared.response?.body?.asString(),
            Array<ProposalGroupResponse>::class.java
        ).toList()

        val proposals = proposalGroupResponse.flatMap { it.links }

        assertEquals(size, proposals.size)

    }

    @And("the response should has two areas")
    fun theResponseShouldHasTwoAreas() {
        val proposalGroupResponse = gson.fromJson(
            stepShared.response?.body?.asString(),
            Array<ProposalGroupResponse>::class.java
        ).toList()

        assertEquals(2, proposalGroupResponse.size)
    }

    @And("total value should be {int}")
    fun totalValueShouldBe(total: Int) {
        val invoiceList = gson.fromJson(
            stepShared.response?.body?.asString(),
            Array<ProposalGroupResponse>::class.java
        ).toList().flatMap { it.links }

        assertEquals(total, invoiceList.map { it.total }.sum().intValueExact())
    }
}
