package br.org.acal.apicore.domain.entity

import br.org.acal.apicore.common.enums.PersonType.INDIVIDUAL
import br.org.acal.apicore.common.enums.PersonType.LEGAL
import br.org.acal.apicore.common.enums.PersonType.UNKNOWN
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DocumentNumberTest{

    private val validCPF = "32672576033"
    private val inValidCPF = "32672576011"

    private val validCNPJ = "58696203000144"
    private val inValidCNPJ = "58696203000155"

    @Test
    fun `when we have a string with 11 characters should type is INDIVIDUAL`(){
        assertEquals(INDIVIDUAL, DocumentNumber(validCPF).type)
    }

    @Test
    fun `when we have a string with 14 characters should type is LEGAL`(){
        assertEquals(LEGAL, DocumentNumber(validCNPJ).type)
    }

    @Test
    fun `when we have a document with size different to 11 or 14 characters should isValid be false`(){
        assertFalse(DocumentNumber("000").isValid)
    }
    @Test
    fun `when we have a document with size different to 11 or 14 characters should isInvalid be true`(){
        assertTrue(DocumentNumber("000").isInvalid)
    }

    @Test
    fun `when we have a string with size different to 11 or 14 characters should type is UNKNOWN`(){
        assertEquals(UNKNOWN, DocumentNumber("000").type)
    }

    @Test
    fun `when we have a VALID CPF should isValid be true`(){
        assertTrue(DocumentNumber(validCPF).isValid)
    }

    @Test
    fun `when we have a VALID CPF should isInValid be false`(){
        assertFalse(DocumentNumber(validCPF).isInvalid)
    }
    @Test
    fun `when we have a INVALID CPF should isValid be false`(){
        assertFalse(DocumentNumber(inValidCPF).isValid)
    }
    @Test
    fun `when we have a INVALID CPF should isInValid be true`(){
        assertTrue(DocumentNumber(inValidCPF).isInvalid)
    }

    @Test
    fun `when we have a VALID CNPJ should isValid be true`(){
        assertTrue(DocumentNumber(validCNPJ).isValid)
    }

    @Test
    fun `when we have a VALID CNPJ should isInValid be false`(){
        assertFalse(DocumentNumber(validCNPJ).isInvalid)
    }
    @Test
    fun `when we have a INVALID CNPJ should isValid be false`(){
        assertFalse(DocumentNumber(inValidCNPJ).isValid)
    }
    @Test
    fun `when we have a INVALID CNPJ should isInValid be true`(){
        assertTrue(DocumentNumber(inValidCNPJ).isInvalid)
    }

    @Test
    fun `when we have a document should hidden the content`(){
        assertEquals("326********" ,DocumentNumber(validCPF).toString())
    }

    @Test
    fun `when we have a invalid document should return the same content`(){
        assertEquals("00" ,DocumentNumber("00").toString())
    }
}