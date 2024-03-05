package br.org.acal.apicore.domain.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ReferenceTest{

    @Test
    fun `when a string is out of pattern should throws runtime Exception`(){
        listOf(
            "THIS_IS_INVALID_STRING",
            "2020.00",
            "2020.13",
            "202010",
            "2020.A0",
            ).forEach {
            assertThrows(RuntimeException::class.java) {
                Reference.of(it)
            }
        }
    }

    @Test
    fun `when has a valid reference should be year and month valid`(){
       Reference.of("2020.01").also {
           assertEquals(2020, it.year.value )
           assertEquals(1, it.month.value )
       }
    }

    @Test
    fun `when we have a valid reference should value like the input`(){
        assertEquals("2020.01", Reference.of("2020.01").value)
    }


}