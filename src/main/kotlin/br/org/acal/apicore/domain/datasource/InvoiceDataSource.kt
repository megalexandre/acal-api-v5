package br.org.acal.apicore.domain.datasource

import br.org.acal.apicore.domain.entity.Invoice

interface InvoiceDataSource: CustomDataSource<Invoice>{
    fun existsInvoiceForReferenceAndLink(invoice: Invoice): Boolean

}