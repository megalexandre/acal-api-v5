package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.Reason

class Cashier(
    val value: Double,
    val reason: Reason,
    val personId: String,
)