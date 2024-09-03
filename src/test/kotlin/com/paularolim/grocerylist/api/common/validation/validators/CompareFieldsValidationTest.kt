package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class CompareFieldsValidationTest {
    private val field = "anyField"
    private val fieldToCompare = "anyOtherField"

    data class FakeInputForTest(
        val anyField: String,
        val anyOtherField: String
    )

    private fun makeSut(): CompareFieldsValidation<Any> {
        return CompareFieldsValidation(field, fieldToCompare)
    }

    @Test
    fun `should return an error if fields do not match`() {
        val sut = makeSut()
        val error = sut.validate(FakeInputForTest("any_value_1", "any_value_2"))
        assertEquals(InvalidParamException(fieldToCompare).message, error?.message)
    }

    @Test
    fun `should return an null if fields match`() {
        val sut = makeSut()
        val result = sut.validate(FakeInputForTest("any_value", "any_value"))
        assertNull(result)
    }

    @Test
    fun `should call CompareFieldsValidation with correct values`() {
        val input = FakeInputForTest("any_value_1", "any_value_2")

        val compareFieldsValidation = mockk<CompareFieldsValidation<FakeInputForTest>>(relaxed = true)

        val capturedInput = slot<FakeInputForTest>()

        every { compareFieldsValidation.validate(capture(capturedInput)) } returns null

        compareFieldsValidation.validate(input)
        verify { compareFieldsValidation.validate(input) }

        assertEquals(input, capturedInput.captured)
    }
}