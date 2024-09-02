package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RequiredFieldValidationTest {
    private val field = "anyField"

    data class FakeInputForValidTest(
        val anyField: String
    )
    data class FakeInputForInvalidTest(
        val anyInvalidField: String
    )

    private fun makeSut(): RequiredFieldValidation<Any> {
        return RequiredFieldValidation(field)
    }

    @Test
    fun `should return a MissingParamException if validation fails`() {
        val sut = makeSut()
        val error = sut.validate(FakeInputForInvalidTest("any_value"))
        assertEquals(MissingParamException(field).message, error?.message)
    }

    @Test
    fun `should return null if validation succeeds`() {
        val sut = makeSut()
        val error = sut.validate(FakeInputForValidTest("any_value"))
        assertNull(error)
    }
}