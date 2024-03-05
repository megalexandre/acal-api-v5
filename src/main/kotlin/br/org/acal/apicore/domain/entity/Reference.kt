package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.util.isValidReference
import java.time.Month
import java.time.Year

data class Reference(
    val year: Year,
    val month: Month
) {
    companion object{
        private const val REGEX_CAST_PATTERN = """(\d{4})\.(\d{2})"""
        private const val YEAR_PAD_SIZE = 4
        private const val MONTH_PAD_SIZE = 2
        private const val PAD_CHAR = '0'
        private const val SEPARATOR = "."
        fun of(value: String): Reference {

            if(!value.isValidReference()){
                throw RuntimeException("this $value is not a valid reference")
            }

            return Regex(REGEX_CAST_PATTERN).find(value)?.let {
                val (year, month ) = it.destructured
                Reference(year = Year.of(year.toInt()), month = Month.of(month.toInt()))
            }!!
        }
    }


    val value: String
        get() = year.value.toString().padStart(YEAR_PAD_SIZE, PAD_CHAR) + SEPARATOR +
                month.value.toString().padStart(MONTH_PAD_SIZE, PAD_CHAR)
}