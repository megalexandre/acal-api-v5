package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.domain.entity.Reference

interface InvoiceDataSource: CustomDataSource<Invoice>{
    fun existsInvoiceForReferenceAndLink(invoice: Invoice): Boolean
    fun findProposal(input: Reference): List<Invoice>

}