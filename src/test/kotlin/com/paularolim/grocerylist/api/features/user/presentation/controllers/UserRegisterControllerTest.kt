package com.paularolim.grocerylist.api.features.user.presentation.controllers

import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.utils.Response
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRegisterControllerTest {
    class ValidationSpy : Validation<Any> {
        var error: Exception? = null
        override fun validate(input: Any): Exception? {
            return error
        }
    }

    data class SutTypes(
        val sut: UserRegisterController,
        val validationSpy: ValidationSpy
    )

    private fun mockRequest(): JsonObject {
        return buildJsonObject {
            put("name", "anyName")
            put("email", "anyEmail")
        }
    }

    private fun makeSut(): SutTypes {
        val validationSpy = ValidationSpy()
        val sut = UserRegisterController(validationSpy)
        return SutTypes(sut, validationSpy)
    }

    @Test
    fun `should return 400 if Validation returns an error`() {
        val (sut, validationSpy) = makeSut()
        val request = mockRequest()

        validationSpy.error = MissingParamException("name")

        val response = runBlocking { sut.handle(request) }

        assertEquals(HttpStatusCode.BadRequest, (response as Response.Error).statusCode)
        assertEquals("Missing param: name", response.error)
    }
}