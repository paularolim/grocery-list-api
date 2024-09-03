package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import io.mockk.*
import kotlin.test.Test
import kotlin.test.assertEquals

class EmailValidationTest {
    private val field = "anyField"

    data class FakeInputForValidTest(
        val anyField: String
    )

    data class FakeInputForInvalidTest(
        val anyField: String
    )

    private fun makeSut(): EmailValidation<Any> {
        return EmailValidation(field)
    }

    @Test
    fun `should return an error if EmailValidator returns false`() {
        val sut = makeSut()
        val email = "any_invalid_email"
        val error = sut.validate(FakeInputForInvalidTest(email))
        assertEquals(InvalidParamException(field).message, error?.message)
    }

    @Test
    fun `should call EmailValidator with correct email`() {
        val input = FakeInputForValidTest("valid_email@example.com")

        val emailValidation = mockk<EmailValidation<FakeInputForValidTest>>(relaxed = true)

        val capturedInput = slot<FakeInputForValidTest>()

        every { emailValidation.validate(capture(capturedInput)) } returns null

        emailValidation.validate(input)
        verify { emailValidation.validate(input) }

        assertEquals(input, capturedInput.captured)
    }
}