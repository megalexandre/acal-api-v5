package br.org.acal.apicore.domain.entity

import java.time.Month
import java.time.Year
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import stub.invoiceNumberStub

class InvoiceNumberTest{

    @Test
    fun test(){
        val firstJanuaryOf2000 = invoiceNumberStub.copy(
            year = Year.of(2000),
            month = Month.JANUARY,
            number = "1"
        )

        assertEquals("2000.01.000001", firstJanuaryOf2000.value)
    }

}