package br.org.acal.apicore.steps

import br.org.acal.apicore.application.rest.address.request.AddressCreateRequest
import br.org.acal.apicore.application.rest.address.request.AreaRequest
import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions
import stub.addressStub

class AddressStepdefs: RestStepDefs() {

    @Given("a address without area is send by post")
    fun aAddressWithoutAreaIsSendByPost() {
        val request = "{\"number\":\"1\",\"letter\":\"A\",\"hasHydrometer\":true}"
        stepShared.response = executePost("address", request)
    }

    @Given("a address without number is send by post")
    fun aAddressWithoutNumberIsSendByPost() {
        val request = "{\"id\":\"01J5B4JPCYTDE6JDBD3MG0FWQG\",\"area\":{\"id\":\"01J5B4JPCXX4KQ9BHWMRJTJ9HX\",\"name\":\"AVENIDA\"},\"letter\":\"A\",\"hasHydrometer\":true,\"active\":true}"
        stepShared.response = executePost("address", request)
    }

    @And("database has address id {string}")
    fun databaseHasAddressId(id: String) {
        val address = addressStub.copy(id = id)
        addressRepository.save(address.toDocument())
    }

    @When("an address with area {string} and number {string} is send by post")
    fun aAddressWithAreaAndNumberIsSendByPost(areaId: String, number: String) {

        val request = AddressCreateRequest(
            number = number,
            area = AreaRequest(id = areaId, name = "anyName" ),
            hasHydrometer = null,
            letter = null,
        )

        stepShared.response = executePost("address", gson.toJson(request))
    }

    @When("an address with the following data is sent by POST")
    fun aAddressWithFollowingDataIsSentByPost(jsonData: String) {
        val request = gson.fromJson(jsonData, AddressCreateRequest::class.java)
        stepShared.response = executePost("address", gson.toJson(request))
    }

    @And("the database needs to have an address with hasHydrometer {string} and letter {string}")
    fun theDatabaseNeedsToHaveAnAddressWithHasHydrometerTrueAndLetter(hasHydrometer: String, letter: String) {
        val addresses = addressRepository.findAll()

        Assertions.assertEquals(1, addresses.size);

        val address = addresses.first()

        Assertions.assertEquals(hasHydrometer.toBoolean(), address.hasHydrometer)
        Assertions.assertEquals(letter, address.letter)
    }


    @Given("database has a address with number {string} and areaId {string}")
    fun databaseHasAAddressWithNumberAndAreaId(number: String, areaId: String) {
        val request = AddressCreateRequest(
            number = number,
            area = AreaRequest(id = areaId, name = "anyName" ),
            hasHydrometer = null,
            letter = null,
        )

        addressRepository.save(request.toEntity().toDocument())
    }
}