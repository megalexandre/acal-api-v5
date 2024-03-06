package br.org.acal.apicore.infrastructure.serializable.`in`

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDate
import java.time.LocalDate.parse
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

class CustomLocalDateDeserializer : JsonDeserializer<LocalDate>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDate =
        parse(p?.text, ISO_LOCAL_DATE)

}

