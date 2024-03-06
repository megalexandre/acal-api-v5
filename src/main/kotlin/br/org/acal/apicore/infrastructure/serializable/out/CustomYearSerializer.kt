package br.org.acal.apicore.infrastructure.serializable.out

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.Year

class CustomYearSerializer : JsonSerializer<Year>() {
    override fun serialize(value: Year?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(value?.value.toString())
    }

}

