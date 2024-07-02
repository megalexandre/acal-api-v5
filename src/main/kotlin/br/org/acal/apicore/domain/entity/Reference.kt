package br.org.acal.apicore.domain.entity

import java.time.Month
import java.time.Year

data class Reference(
    val year: Year,
    val month: Month
) {
    companion object{
        private const val YEAR_PAD_SIZE = 4
        private const val MONTH_PAD_SIZE = 2
        private const val PAD_CHAR = '0'
        private const val SEPARATOR = "."

        fun of(value: String): Reference =
            runCatching {
                val (year, month)= value.split(SEPARATOR).map { it.toInt() }

                Reference(
                    year = Year.of(year),
                    month = Month.of(month)
                )

            }.getOrElse {
                throw RuntimeException("this $value is not a valid reference")
            }
    }

    val value: String
        get() = year.value.toString().padStart(YEAR_PAD_SIZE, PAD_CHAR) + SEPARATOR +
                month.value.toString().padStart(MONTH_PAD_SIZE, PAD_CHAR)
}