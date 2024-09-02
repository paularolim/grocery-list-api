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
        var input: Any? = null
        var error: Exception? = null
        override fun validate(input: Any): Exception? {
            this.input = input
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
    fun `should return 201 if valid data is provided`() {
        val (sut) = makeSut()
        val request = mockRequest()

        val response = runBlocking { sut.handle(request) }

        assertEquals(HttpStatusCode.Created, (response as Response.Success).statusCode)
        assertEquals("Success message", response.message)
    }

    @Test
    fun `should call Validation with correct value`() {
        val (sut, validationSpy) = makeSut()
        val requestJson = mockRequest()

        val request = UserRegisterController.UserRegisterControllerRequest(
            name = requestJson["name"]?.jsonPrimitive?.contentOrNull,
            email = requestJson["email"]?.jsonPrimitive?.contentOrNull
        )

        runBlocking { sut.handle(requestJson) }

        assertEquals(validationSpy.input, request)
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