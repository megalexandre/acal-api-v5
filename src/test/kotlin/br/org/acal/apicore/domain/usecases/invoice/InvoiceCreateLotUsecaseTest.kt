package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.domain.entity.Invoice
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import stub.invoiceStub

class InvoiceCreateLotUsecaseTest {

    private val dataSource: InvoiceDataSource = mockk()

    private val useCase = InvoiceCreateLotUsecase(
        dataSource = dataSource
    )

    @Test
    fun `when we have a invoice with de same link and reference should do not save`(){
        val invoice = invoiceStub
        every { dataSource.existsInvoiceForReferenceAndLink(invoice) } returns true

        assertThrows(InvalidUsecaseException::class.java) {
            useCase.execute(listOf(invoice))
        }
    }

    @Test
    fun `when save a invoice first time by reference and link should do save`(){
        val invoice = invoiceStub
        every { dataSource.existsInvoiceForReferenceAndLink(invoice) } returns false
        every { dataSource.saveAll(any<List<Invoice>>()) } just Runs

        useCase.execute(listOf(invoice))

        verify { dataSource.existsInvoiceForReferenceAndLink(invoice) }
    }

}