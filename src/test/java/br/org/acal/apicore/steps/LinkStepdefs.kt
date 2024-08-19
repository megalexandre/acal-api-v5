package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.link.request.LinkCreateRequest
import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.Given
import stub.linkStub

class LinkStepdefs: RestStepDefs() {

    @Given("a link category {string} and customer {string} and address {string} is send by post")
    fun aLinkCategoryAndCustomerAndAddressIsSendByPost(categoryId: String, customerId: String, addressId: String) {

        val linkCreateRequest = LinkCreateRequest(
            categoryId = categoryId,
            customerId = customerId,
            addressId = addressId,
        )

        stepShared.response = executePost("link", gson.toJson(linkCreateRequest))
    }

    @Given("database a link with Id {string}")
    fun databaseALinkWithId(id: String) {
        val link = linkStub.copy(id = id)
        linkRepository.save(link.toDocument())

    }
}