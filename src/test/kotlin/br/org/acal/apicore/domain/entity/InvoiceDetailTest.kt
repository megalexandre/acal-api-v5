package br.org.acal.apicore.domain.entity

import java.time.LocalDateTime.now
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import stub.invoiceDetailStub

class InvoiceDetailTest{

    @Test
    fun `when dataPaid is null should paid is false`(){
        val notPaidDetail = invoiceDetailStub.copy(dataPaid = null)
        assertFalse(notPaidDetail.isPaid)
        assertTrue(notPaidDetail.isNotPaid)
    }

    @Test
    fun `when dataPaid is not null should paid is true`(){
        val notPaidDetail = invoiceDetailStub.copy(dataPaid = now())
        assertTrue(notPaidDetail.isPaid)
        assertFalse(notPaidDetail.isNotPaid)
    }

}