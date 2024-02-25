package steps.category

import br.org.acal.apicore.ApplicationTests
import io.cucumber.java8.Pt
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions
import stub.categoryCreateStub

class CategoryStepdefs: ApplicationTests(), Pt {

    init {
        Quando("^: faco um post para /category$") {
            val header = mutableMapOf<String,String>()
            header["Content-Type"] = "application/json"

            Given {
                headers(header)
                body(gson.toJson(categoryCreateStub))
            } When {
                post("category")
            } Then {
                statusCode(HttpStatus.SC_CREATED)
                body("$", Matchers.hasKey("id"))
            }
        }

        Então("^: a resposta deve conter ser um Ok (\\d+) com o ID da nova categoria$") { id: String ->
            Assertions.assertEquals(201, id)
        }
    }
}