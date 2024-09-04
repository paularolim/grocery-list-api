package com.paularolim.grocerylist.api.common.validation.validators

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import kotlin.test.Test
import kotlin.test.assertEquals


class MinLengthValidationTest {
    private val field = "anyField"

    data class FakeInputForTest(
        val anyField: String
    )

    private fun makeSut(): MinLengthValidation<Any> {
        return MinLengthValidation(field, 10)
    }

    @Test
    fun `should return a InvalidParamException if validation fails`() {
        val sut = makeSut()
        val error = sut.validate(FakeInputForTest("any"))
        assertEquals(InvalidParamException(field).message, error?.message)
    }
}