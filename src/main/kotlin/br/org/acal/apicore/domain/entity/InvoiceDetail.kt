package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.Reason
import java.math.BigDecimal
import java.time.LocalDateTime

data class InvoiceDetail(

    val reason: Reason,
    val value: BigDecimal,
    val dataPaid: LocalDateTime?,

)  {

    val isPaid: Boolean = dataPaid != null
    val isNotPaid: Boolean = dataPaid == null

}

