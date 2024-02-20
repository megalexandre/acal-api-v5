package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.Fixtures.Companion.SEPARATOR
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
    }

    constructor(value: String) : this(
        year = Year.of(value.split(".")[0].toInt()),
        month = Month.of(value.split(".")[1].toInt()),
    )

    val value: String
        get() = year.value.toString().padStart(YEAR_PAD_SIZE, PAD_CHAR) + SEPARATOR +
                month.value.toString().padStart(MONTH_PAD_SIZE, PAD_CHAR)
}