package br.org.acal.apicore.infrastructure.serializable.out

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

class CustomLocalDateSerializer : JsonSerializer<LocalDate>() {
    override fun serialize(value: LocalDate?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(ISO_LOCAL_DATE.format(value))
    }

}

