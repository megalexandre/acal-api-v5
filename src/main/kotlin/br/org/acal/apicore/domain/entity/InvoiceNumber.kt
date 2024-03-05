package br.org.acal.apicore.domain.entity

import java.time.Month
import java.time.Year

data class InvoiceNumber(
    val year: Year,
    val month: Month,
    val number: String,
) {

    companion object{
        private const val YEAR_INDEX = 0
        private const val MONTH_INDEX = 1
        private const val NUMBER_INDEX = 2
        private const val STRING_SEPARATOR = "."
        private const val MONTH_PAD_SIZE = 2
        private const val NUMBER_PAD_SIZE = 6
        private const val PAD_CHAR = '0'

        fun of(value: String): InvoiceNumber =
           runCatching {
                InvoiceNumber(
                    year = Year.of(value.split(STRING_SEPARATOR)[YEAR_INDEX].toInt()),
                    month = Month.of(value.split(STRING_SEPARATOR)[MONTH_INDEX].toInt()),
                    number = value.split(STRING_SEPARATOR)[NUMBER_INDEX],
                )
            }.getOrElse {
                throw RuntimeException("$value is not a valid InvoiceNumber")
            }
        }

    val value: String
        get() = "${year.value}" + STRING_SEPARATOR +
                month.value.toString().padStart(MONTH_PAD_SIZE,PAD_CHAR) + STRING_SEPARATOR +
                number.padStart(NUMBER_PAD_SIZE, PAD_CHAR)

    val reference: Reference
        get() = Reference(year = year, month = month)

}