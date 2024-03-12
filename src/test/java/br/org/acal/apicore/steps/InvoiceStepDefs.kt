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
        super.resetDatabase()

        val reference = Reference.of(referenceString)
        val linkId = "01HQ34HPVK0RRA7CA2E2MNK37H"

        val linkStub = linkStub.copy(
            id = linkId,
            active = true,
        ).toDocument()

        val invoiceStub = invoiceStub.copy(
            linkId = linkId,
            reference = reference,
        ).toDocument()

        invoiceRepository.save(invoiceStub)
        linkRepository.save(linkStub)
    }

    @Given("database has two active in different areas link without invoice for reference {string}")
    fun databaseHasTwoActiveLinkWithoutInvoiceForReference(referenceString: String) {
        val linkStubOne = linkStub.copy(
            id = random(),
            customer = customerStub.copy(name = "C | I'm valid"),
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
            customer = customerStub.copy(name = "D| I'm valid too"),
            address = addressStub.copy(
                area = areaStub.copy(
                    name = "Avenida Morro do chapeu"
                ),
            ),
            suspended = false,
            active = true,
        )

        linkRepository.saveAll(listOf(
            linkStubOne.toDocument(),
            linkStubTwo.toDocument())
        )
    }

    @Given("database has inactive link with invoice for reference {string}")
    fun databaseHasInactiveLinkWithInvoiceForReference(referenceString: String) {
        val linkStubInactive = linkStub.copy(
            customer = customerStub.copy(name = "B | I'm inactive"),
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
