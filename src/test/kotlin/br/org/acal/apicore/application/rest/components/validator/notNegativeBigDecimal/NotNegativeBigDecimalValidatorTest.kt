package br.org.acal.apicore.application.rest.components.validator.notNegativeBigDecimal

import br.org.acal.apicore.application.components.validator.notNegativeBigDecimal.NotNegativeBigDecimalValidator
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NotNegativeBigDecimalValidatorTest {

    private val validator = NotNegativeBigDecimalValidator()
    @Test
    fun `when value is less then zero should be false`() {
        assertFalse(validator.isValid(BigDecimal(-1), null))
    }

    @Test
    fun `when value is equal zero should be false`() {
        assertFalse(validator.isValid(ZERO, null))
    }

    @Test
    fun `when value is more then zero should be true`() {
        assertTrue(validator.isValid(ONE, null))
    }
}