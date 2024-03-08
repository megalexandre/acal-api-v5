package adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

class LocalDateTimeTypeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    override fun serialize(
        date: LocalDateTime,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement  = JsonPrimitive(date.format(ISO_LOCAL_DATE_TIME))

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDateTime = parse(json.asString, ISO_LOCAL_DATE_TIME)
}
