package br.org.acal.apicore.steps

import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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

    @And("the database need has a {string} with returned id")
    fun theDatabaseNeedHasAWithReturnedId(name: String) {

        val id = stepShared.get("id")
        when(name){

            "category" ->{
                assertTrue(categoryRepository.findById(id).isPresent)
            }

            else ->  throw RuntimeException()
        }
    }

    @And("database the size of the {string} document should be {long}")
    fun databaseTheSizeOfTheDocumentShouldBe(document: String, size: Long) {

        when(document){
            "category" ->{
                assertEquals(size, categoryRepository.count())
            }

            "address" -> {
                assertEquals(size, addressRepository.count())
            }

            else ->  throw RuntimeException()
        }

    }
}