package br.org.acal.apicore.steps

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.restassured.response.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("cucumber-glue")
class StepShared {

    var response: Response? = null

    @Autowired
    lateinit var gson: Gson

    fun get(key: String): String =
         gson.fromJson(response?.body?.asString(), JsonObject::class.java)
             .getAsJsonPrimitive(key).asString

}
