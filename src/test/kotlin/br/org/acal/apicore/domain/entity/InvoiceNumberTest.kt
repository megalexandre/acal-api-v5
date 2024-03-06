package br.org.acal.apicore.domain.entity

import java.time.Month
import java.time.Year
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import stub.invoiceNumberStub

class InvoiceNumberTest{

    @Test
    fun `when string is invalid invoice number should throws exception`(){
        assertThrows(RuntimeException::class.java) {
            InvoiceNumber.of("2000011")
        }
    }
    @Test
    fun `when create a invoice number by string should values be correctly`(){

        val invoice = InvoiceNumber.of("2000.01.1")

        assertEquals(2000, invoice.year.value)
        assertEquals(1, invoice.month.value)
        assertEquals("1", invoice.number)

        assertEquals("2000.01.000001", invoice.value)
        assertEquals(Reference.of("2000.1"), invoice.reference)
    }

    @Test
    fun `when create a invoice number should format the value`(){
        val firstJanuaryOf2000 = invoiceNumberStub.copy(
            year = Year.of(2000),
            month = Month.JANUARY,
            number = "1"
        )

        assertEquals("2000.01.000001", firstJanuaryOf2000.value)
    }

}