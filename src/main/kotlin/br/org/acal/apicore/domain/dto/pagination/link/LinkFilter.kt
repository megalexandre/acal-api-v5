package br.org.acal.apicore.domain.dto.pagination.link

import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter

class LinkFilter(

    val id: String? = null,
    val active: Boolean? = null,
    val categoryId: String? = null,

): DefaultFilter