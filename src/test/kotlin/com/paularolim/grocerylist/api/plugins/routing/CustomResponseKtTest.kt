package com.paularolim.grocerylist.api.plugins.routing

import com.paularolim.grocerylist.api.features.user.presentation.controllers.UserX
import com.paularolim.grocerylist.api.utils.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomResponseKtTest {
    private data class UserTest(val name: String)

    @MockK
    private lateinit var call: ApplicationCall

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { call.respond(any<HttpStatusCode>(), any<Map<String, String>>()) } just Runs
        coEvery { call.respond(any<HttpStatusCode>(), any()) } just Runs
    }

    @AfterTest
    fun tearDown() {
        clearMocks(call)
    }

    @Test
    fun `should respond with Success`() = runBlocking {
        val response = Response.Success(message = "Operation successful")
        call.customResponse(response)

        coVerify {
            call.respond(
                HttpStatusCode.OK,
                mapOf("message" to "Operation successful")
            )
        }
    }

    @Test
    fun `should respond with SuccessWithData`() = runBlocking {
        val user = UserTest(name = "Sample User")
        val response = Response.SuccessWithData(data = user)
        call.customResponse(response)

        val statusCodeSlot = slot<HttpStatusCode>()
        val dataSlot = slot<Any>()

        coVerify {
            call.respond(capture(statusCodeSlot), capture(dataSlot))
        }

        assertEquals(HttpStatusCode.OK, statusCodeSlot.captured)
        assertEquals(user, dataSlot.captured)
    }

    @Test
    fun `should respond with Error`() = runBlocking {
        val response = Response.Error(
            error = "Something went wrong",
            statusCode = HttpStatusCode.BadRequest
        )
        call.customResponse(response)

        coVerify {
            call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "statusCode" to HttpStatusCode.BadRequest.value.toString(),
                    "error" to "Something went wrong"
                )
            )
        }
    }

    @Test
    fun `should respond with custom status code`() = runBlocking {
        val response = Response.Success(message = "Created", statusCode = HttpStatusCode.Created)
        call.customResponse(response)

        coVerify {
            call.respond(
                HttpStatusCode.Created,
                mapOf("message" to "Created")
            )
        }
    }
}
