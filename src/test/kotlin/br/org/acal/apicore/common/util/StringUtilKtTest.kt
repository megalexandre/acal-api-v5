package br.org.acal.apicore.common.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StringUtilKtTest {

    @Test
    fun `when the string matches regex expression should be true`() {
        listOf(
            "2020.01",
            "2020.02",
            "2020.03",
            "2020.04",
            "2020.05",
            "2020.06",
            "2020.07",
            "2020.08",
            "2020.09",
            "2020.10",
            "2020.11",
            "2020.12",
        ).forEach {
            assertTrue(it.isValidReference())
        }
    }

    @Test
    fun `when the string not matches regex expression should be false`() {
        assertFalse("202001".isValidReference())
    }

    @Test
    fun `when the mouth is 00 should be false`() {
        assertFalse("2020.00".isValidReference())
    }

    @Test
    fun `when the mouth is more than 12 should be false`() {
        assertFalse("2020.13".isValidReference())
    }

    @Test
    fun `when the string has any letter should be false`() {
        assertFalse("20200A".isValidReference())
    }
    @Test
    fun `when the string has no dot should be false`() {
        assertFalse("202001".isValidReference())
    }

}