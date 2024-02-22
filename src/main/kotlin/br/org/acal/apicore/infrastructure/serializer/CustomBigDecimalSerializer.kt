package br.org.acal.apicore.infrastructure.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal
import java.math.RoundingMode

class CustomBigDecimalSerializer : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(value?.setScale(2, RoundingMode.HALF_UP).toString())
    }

}

