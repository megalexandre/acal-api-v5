package br.org.acal.apicore.domain.entity

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.TEN
import java.time.LocalDate
import java.time.LocalDateTime.now
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import stub.invoiceDetailStub
import stub.invoiceNotPaidStub
import stub.invoicePaidStub
import stub.invoiceStub

class InvoiceTest {


    @Test
    fun `when a invoice has daysInOverDue more then 60 should be in cancellationOfRisk`() {

        val cancellationOfRisk = invoiceNotPaidStub.copy(
            dueDate = LocalDate.now().minusDays(60)
        ).cancellationOfRisk

        assertTrue(cancellationOfRisk)
    }

    @Test
    fun `when a invoice has daysInOverDue less then 60 should be in cancellationOfRisk`() {

        val cancellationOfRisk = invoiceNotPaidStub.copy(
            dueDate = LocalDate.now().minusDays(59)
        ).cancellationOfRisk

        assertFalse(cancellationOfRisk)
    }

    @Test
    fun `when a invoice is not pay should daysInOverDue `() {

        val daysInOverDue = invoiceNotPaidStub.copy(
            dueDate = LocalDate.now().minusDays(30)
        ).daysInOverDue

        assertEquals(30, daysInOverDue)
    }

    @Test
    fun `when a invoice is payed should daysInOverDue be ZERO`() {

        val daysInOverDue = invoicePaidStub.copy(
            dueDate = LocalDate.now().plusDays(30)
        ).daysInOverDue

        assertEquals(0, daysInOverDue)
    }
    @Test
    fun `when invoice has not details should not be created`() {

        val error = assertThrows(IllegalArgumentException::class.java) {
            invoiceStub.copy(
                invoiceDetails = emptyList()
            )
        }

        assertEquals("invoice without details is not valid", error.localizedMessage)
    }

    @Test
    fun `when invoice has many details total should be de sum of all`() {
        val totalValue = invoiceStub.copy(
            invoiceDetails = listOf(
                invoiceDetailStub.copy(value = ONE),
                invoiceDetailStub.copy(value = TEN),
            )
        ).total

        assertEquals(BigDecimal(11), totalValue)
    }

    @Test
    fun `when invoice has details paid and not paid should totalAwaitingPayment sum only the not paid`() {
        val totalAwaitingPayment = invoiceStub.copy(
            invoiceDetails = listOf(
                invoiceDetailStub.copy(value = ONE, dataPaid = now()),
                invoiceDetailStub.copy(value = TEN, dataPaid = null),
            )
        ).awaitingPaymentValue

        assertEquals(TEN, totalAwaitingPayment)
    }

    @Test
    fun `when invoice has details paid and not paid should totalPaidValue sum only the paid`() {
        val totalPaidValue = invoiceStub.copy(
            invoiceDetails = listOf(
                invoiceDetailStub.copy(value = ONE, dataPaid = now()),
                invoiceDetailStub.copy(value = TEN, dataPaid = null),
            )
        ).paidValue

        assertEquals(ONE, totalPaidValue)
    }

    @Test
    fun `when invoice has details paid and not paid should to be equal to paid and not paid`() {
        val invoice = invoiceStub.copy(
            invoiceDetails = listOf(
                invoiceDetailStub.copy(value = ONE, dataPaid = now()),
                invoiceDetailStub.copy(value = TEN, dataPaid = null),
            )
        )

        val total = invoice.total
        val paid = invoice.paidValue
        val notPaid = invoice.awaitingPaymentValue

        assertEquals(total, paid + notPaid)
    }

    @Test
    fun `when invoice has all details paid should is paid be true`() {
        val invoice = invoiceStub.copy(
            invoiceDetails = listOf(
                invoiceDetailStub.copy(dataPaid = now()),
                invoiceDetailStub.copy(dataPaid = now()),
            )
        )
        assertTrue(invoice.isPayed)
        assertFalse(invoice.isNotPayed)
    }

    @Test
    fun `when invoice has at least on detail not paid should is paid be false`() {
        val invoice = invoiceStub.copy(
            invoiceDetails = listOf(
                invoiceDetailStub.copy(dataPaid = null),
                invoiceDetailStub.copy(dataPaid = now()),
            )
        )
        assertFalse(invoice.isPayed)
        assertTrue(invoice.isNotPayed)
    }

    @Test
    fun `when dueDate is after now and invoice is not paid should isOverDue be true`(){
        val invoiceNotPaidStub = invoiceNotPaidStub.copy(
            dueDate = LocalDate.now().minusMonths(1)
        )

        assertTrue(invoiceNotPaidStub.isOverDue)

    }

    @Test
    fun `when dueDate is before now and invoice is not paid should isOverDue be false`(){
        val invoiceNotPaidStub = invoiceNotPaidStub.copy(
            dueDate = LocalDate.now().plusMonths(1)
        )

        assertFalse(invoiceNotPaidStub.isOverDue)
    }


    @Test
    fun `when dueDate is before now and invoice is paid should isOverDue be false`(){
        val invoicePaidStub = invoicePaidStub.copy(
            dueDate = LocalDate.now().plusMonths(1)
        )

        assertFalse(invoicePaidStub.isOverDue)
    }

}