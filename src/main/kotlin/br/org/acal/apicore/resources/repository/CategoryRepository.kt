package br.org.acal.apicore.resources.repository

import br.org.acal.apicore.common.enums.CategoryType
import br.org.acal.apicore.domain.entity.Category
import br.org.acal.apicore.resources.document.CategoryDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository : MongoRepository<CategoryDocument, String>{
    fun findByNameAndType(name: String, type: CategoryType): Category?
}
