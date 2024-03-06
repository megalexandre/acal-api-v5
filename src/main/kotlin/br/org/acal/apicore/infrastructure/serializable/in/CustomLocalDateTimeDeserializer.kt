package br.org.acal.apicore.infrastructure.serializable.`in`

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDateTime
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter.ISO_DATE_TIME

class CustomLocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime =
        parse(p?.text, ISO_DATE_TIME)


}



