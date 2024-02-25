package br.org.acal.apicore

import br.org.acal.apicore.resources.repository.CategoryRepository
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus.SC_CREATED
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import stub.categoryCreateStub

class CategoryCreateNew: ApplicationTests() {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository;

    @Test
    fun `WHEN receiver a valid category SHOULD return CREATED 201`(){

        val header = mutableMapOf<String,String>()
        header["Content-Type"] = "application/json"

        Given {
            headers(header)
            body(gson.toJson(categoryCreateStub))
        } When {
            post("category")
        } Then {
            statusCode(SC_CREATED)
            body("$", hasKey("id"))
        }
    }

}