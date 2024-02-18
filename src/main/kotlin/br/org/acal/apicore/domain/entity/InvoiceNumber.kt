package br.org.acal.apicore.domain.entity

import java.time.Month
import java.time.Year

class InvoiceNumber(
    val year: Year,
    val month: Month,
    val number: String,
) {

    constructor(number: String): this (
        year = Year.of(number.split('.')[0].toInt()),
        month = Month.of(number.split('.')[1].toInt()),
        number = number.split('.')[2],
    )
    val value: String
        get() = "${month.value}.${year.value}/$number"
}