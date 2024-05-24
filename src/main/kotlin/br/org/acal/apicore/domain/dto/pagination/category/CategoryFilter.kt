package br.org.acal.apicore.domain.dto.pagination.category

import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.dto.pagination.pages.DefaultFilter

class CategoryFilter(

    val id: String? = null,
    val name: String? = null,
    val type: CategoryType? = null,
    val water: String ?= null,
    val partner: String ?= null,

): DefaultFilter


