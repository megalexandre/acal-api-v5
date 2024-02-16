package br.org.acal.apicore.domain.dto.pagination.customer

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.entity.PhoneNumber
import java.time.LocalDate
import java.time.LocalDateTime

class CustomerFilter(

    val id: String? = null,
    val name: String? = null,
    val documentNumber: String ? = null,
    var birthDay: LocalDate? = null,
    val phoneNumbers: List<PhoneNumber>? = null,
    val active: Boolean? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,

): DefaultFilter