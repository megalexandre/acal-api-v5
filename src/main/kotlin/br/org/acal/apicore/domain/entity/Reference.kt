package br.org.acal.apicore.domain.entity

import java.time.Month
import java.time.Year

data class Reference(
    val year: Year,
    val month: Month
) {
    constructor(value: String) : this(
        year = Year.of(value.split(".")[0].toInt()),
        month = Month.of(value.split(".")[1].toInt()),
    )
}