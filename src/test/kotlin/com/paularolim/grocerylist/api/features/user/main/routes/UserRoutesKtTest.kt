package com.paularolim.grocerylist.api.features.user.main.routes

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class UserRoutesKtTest {
    @Test
    fun `Should return 201 on register`() = testApplication {
        val requestBody = """
            {
                "name": "any_name",
                "email": "valid_email@example.com",
                "password": "any_password",
                "passwordConfirmation": "any_password"
            }
        """.trimIndent()
        val responseBody = """{"message":"Success message"}"""

        val response = client.post("/users/register") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

        assertEquals(HttpStatusCode.Created, response.status)
        assertEquals(responseBody, response.body())
    }
}