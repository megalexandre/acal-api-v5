package br.org.acal.apicore.domain.entity

import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import stub.categoryStub

class CategoryTest{

    @Test
    fun `when has many values should sum`(){
        val categoryTotal = categoryStub.total
        val total = BigDecimal(20)

        assertEquals(0,
            total.compareTo(categoryTotal.setScale(10, HALF_UP))
        )

    }

}