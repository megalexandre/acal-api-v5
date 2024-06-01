package br.org.acal.apicore.domain.dto.pagination.address

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter

data class AddressFilter(

    val id: String? = null,
    val area: String ? = null,
    val number: String ? = null,
    val letter: String ? = null,
    val hasHydrometer: Boolean ? = null,
    val active: Boolean? = null,

    ): DefaultFilter