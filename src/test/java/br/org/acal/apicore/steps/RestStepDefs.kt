package br.org.acal.apicore.steps

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response

class RestStepDefs: SpringIT() {

    fun executeGet(url: String, filter: Map<String, String> = mapOf()): Response {
        val header = mutableMapOf<String, String>()
        header["Content-Type"] = "application/json"

        return Given {
            headers(header)
            params(filter)
        } When {
            get("http://localhost:$serverPort/$url")
        }
    }

    fun executePost(url: String, body: String): Response {
        val header = mutableMapOf<String, String>()
        header["Content-Type"] = "application/json"

        return Given {
            headers(header)
            body(body)
        } When {
            post("http://localhost:$serverPort/$url")
        }
    }

}