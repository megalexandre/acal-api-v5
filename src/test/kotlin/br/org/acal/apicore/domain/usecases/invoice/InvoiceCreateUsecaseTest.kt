package br.org.acal.apicore.domain.usecases.invoice

import br.org.acal.apicore.domain.datasource.InvoiceDataSource
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import stub.invoiceStub

class InvoiceCreateUsecaseTest{

    private val dataSource: InvoiceDataSource = mockk()

    private val useCase = InvoiceCreateUsecase(dataSource = dataSource)

    @Test
    fun `given link has no invoice for reference when execute then save it `(){
        every { dataSource.findByFilter(any()) } returns listOf()
        every { dataSource.save(any()) } returns invoiceStub

        val invoice = invoiceStub

        useCase.execute(invoice)

        verify(exactly = 1) { dataSource.save(any()) }
    }

    @Test
    fun `given link has invoice for reference when execute then throw `(){
        every { dataSource.findByFilter(any()) } returns listOf(invoiceStub)
        every { dataSource.save(any()) } returns invoiceStub

        val invoice = invoiceStub

        assertThrows(InvalidUsecaseException::class.java) {
            useCase.execute(invoice)
        }

        verify(exactly = 0) { dataSource.save(any()) }
    }

}