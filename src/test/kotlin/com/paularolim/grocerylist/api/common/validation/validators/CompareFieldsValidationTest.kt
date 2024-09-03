package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import kotlin.test.Test
import kotlin.test.assertEquals


class CompareFieldsValidationTest {
    private val field = "anyField"
    private val fieldToCompare = "anyField"

    data class FakeInputForInvalidTest(
        val anyField: String,
        val anyOtherField: String
    )

    private fun makeSut(): CompareFieldsValidation<Any> {
        return CompareFieldsValidation(field, fieldToCompare)
    }

    @Test
    fun `should return an error if fields do not match`() {
        val sut = makeSut()
        val error = sut.validate(FakeInputForInvalidTest("any_value_1", "any_value_2"))
        assertEquals(InvalidParamException(fieldToCompare).message, error?.message)
    }
}