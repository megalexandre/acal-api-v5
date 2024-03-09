package br.org.acal.apicore.domain.dto.pagination.invoice

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter
import br.org.acal.apicore.domain.entity.Reference

class InvoiceFilter(

    val id: String? = null,
    val linkId: String? = null,
    val reference: Reference? = null,

    ): DefaultFilter