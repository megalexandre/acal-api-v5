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
    }

    constructor(number: String): this (
        year = Year.of(number.split(STRING_SEPARATOR)[YEAR_INDEX].toInt()),
        month = Month.of(number.split(STRING_SEPARATOR)[MONTH_INDEX].toInt()),
        number = number.split(STRING_SEPARATOR)[NUMBER_INDEX],
    )
    val value: String
        get() = month.value.toString().padStart(MONTH_PAD_SIZE,PAD_CHAR) + STRING_SEPARATOR +
                "${year.value}" + STRING_SEPARATOR +
                number.padStart(NUMBER_PAD_SIZE, PAD_CHAR)


}