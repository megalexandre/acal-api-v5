package br.org.acal.apicore.common.util

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.math.RoundingMode.HALF_DOWN

fun BigDecimal.toMonetary(): BigDecimal =
    when(this){
        ZERO -> ZERO
        else -> this.setScale(9, HALF_DOWN)
    }


fun List<BigDecimal>?.sum(): BigDecimal =
    this?.fold(ZERO) { acc, expense -> acc + expense }
        ?.toMonetary()
        ?: ZERO
