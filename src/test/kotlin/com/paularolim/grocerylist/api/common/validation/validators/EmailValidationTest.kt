package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import kotlin.test.Test
import kotlin.test.assertEquals

class EmailValidationTest {
    private val field = "anyField"

    data class FakeInputForInvalidTest(
        val anyField: String
    )

    private fun makeSut(): EmailValidation<FakeInputForInvalidTest> {
        return EmailValidation(field)
    }

    @Test
    fun `should return an error if EmailValidator returns false`() {
        val sut = makeSut()
        val email = "any_invalid_email"
        val error = sut.validate(FakeInputForInvalidTest(email))
        assertEquals(InvalidParamException(field).message, error?.message)
    }
}