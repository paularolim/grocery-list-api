package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ValidationCompositeTest {
    private val field = "anyField"

    class ValidationSpy : Validation<Map<String, Any?>> {
        var error: Exception? = null
        override fun validate(input: Map<String, Any?>): Exception? {
            return error
        }
    }

    data class SutTypes(
        val sut: ValidationComposite<Map<String, Any?>>,
        val validationSpies: List<ValidationSpy>
    )

    private fun makeSut(): SutTypes {
        val validationSpies = listOf(
            ValidationSpy(),
            ValidationSpy()
        )
        val sut = ValidationComposite(validationSpies)
        return SutTypes(sut, validationSpies)
    }

    @Test
    fun `should return an error if any validation fails`() {
        val (sut, validationSpies) = makeSut()
        validationSpies[1].error = MissingParamException(field)
        val error = sut.validate(mapOf(field to "anyValue"))
        assertEquals(validationSpies[1].error, error)
    }

    @Test
    fun `Should return the first error if more than one validation fails`() {
        val (sut, validationSpies) = makeSut()
        validationSpies[0].error = Exception()
        validationSpies[1].error = MissingParamException(field)
        val error = sut.validate(mapOf(field to "anyValue"))
        assertEquals(validationSpies[0].error, error)
    }

    @Test
    fun `Should not return if validation succeeds`() {
        val (sut) = makeSut()
        val error = sut.validate(mapOf(field to "anyValue"))
        assertNull(error)
    }
}