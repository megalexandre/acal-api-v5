package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category

interface CategoryDataSource: CustomDataSource<Category>{
    fun findByNameAndType(name: String, type: CategoryType): Category ?
}