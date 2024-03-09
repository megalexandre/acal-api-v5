package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.domain.entity.Link
import br.org.acal.apicore.domain.entity.Reference

interface LinkDataSource: CustomDataSource<Link>{
    fun findFindAllWithoutInvoiceForReferenceUsecase(input: Reference): List<Link>
}