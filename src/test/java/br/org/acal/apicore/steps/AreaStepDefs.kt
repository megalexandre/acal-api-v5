package br.org.acal.apicore.steps

import br.org.acal.apicore.resources.document.adapter.toDocument
import io.cucumber.java.en.Given
import stub.areaStub

class AreaStepDefs: RestStepDefs() {

    @Given("database has an area with id {string}")
    fun databaseHasAnAreaWithId(id: String) {
        areaRepository.save(areaStub.copy(id = id).toDocument())
    }
}