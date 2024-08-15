package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.link.request.LinkCreateRequest
import io.cucumber.java.en.Given

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

}