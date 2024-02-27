package br.org.acal.apicore

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus.SC_CREATED
import org.junit.jupiter.api.Test

class TodoIT: ApplicationTests() {

    @Test
    fun `WHEN receiver a valid category SHOULD return CREATED 201`(){

        val header = mutableMapOf<String,String>()
        header["Content-Type"] = "application/json"

        Given {
            headers(header)
        } When {
            get("todo")
        } Then {
            statusCode(SC_CREATED)
        }
    }

}