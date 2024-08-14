package br.org.acal.apicore.steps

import io.cucumber.java.en.Given
import stub.addressCreateRequest

class AddressStepdefs: RestStepDefs() {

    @Given("a address without area is send by post")
    fun aAddressWithoutAreaIsSendByPost() {
        stepShared.response = executePost("address", gson.toJson(
            addressCreateRequest
        ))
    }
}