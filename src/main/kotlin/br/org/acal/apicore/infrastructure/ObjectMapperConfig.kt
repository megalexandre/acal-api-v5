package br.org.acal.apicore.infrastructure

import br.org.acal.apicore.infrastructure.deserializer.CustomLocalDateDeserializer
import br.org.acal.apicore.infrastructure.deserializer.CustomLocalDateTimeDeserializer
import br.org.acal.apicore.infrastructure.serializer.CustomBigDecimalSerializer
import br.org.acal.apicore.infrastructure.serializer.CustomLocalDateSerializer
import br.org.acal.apicore.infrastructure.serializer.CustomLocalDateTimeSerializer
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature.NullIsSameAsDefault
import com.fasterxml.jackson.module.kotlin.KotlinFeature.NullToEmptyCollection
import com.fasterxml.jackson.module.kotlin.KotlinFeature.NullToEmptyMap
import com.fasterxml.jackson.module.kotlin.KotlinFeature.SingletonSupport
import com.fasterxml.jackson.module.kotlin.KotlinFeature.StrictNullChecks
import com.fasterxml.jackson.module.kotlin.KotlinModule.Builder
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig{

    fun createSerializer(): SimpleModule =
        SimpleModule().apply {
            addSerializer(LocalDate::class.java, CustomLocalDateSerializer())
            addSerializer(LocalDateTime::class.java, CustomLocalDateTimeSerializer())
            addSerializer(BigDecimal::class.java, CustomBigDecimalSerializer())
            addDeserializer(LocalDate::class.java, CustomLocalDateDeserializer())
            addDeserializer(LocalDateTime::class.java, CustomLocalDateTimeDeserializer())
        }

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(FAIL_ON_EMPTY_BEANS)
        .registerModule(createSerializer())
        .registerModule(
            Builder()
                .withReflectionCacheSize(512)
                .configure(NullToEmptyCollection, false)
                .configure(NullToEmptyMap, false)
                .configure(NullIsSameAsDefault, false)
                .configure(SingletonSupport, false)
                .configure(StrictNullChecks, false)
            .build()
        )



}

