package com.paularolim.grocerylist.api.plugins.routing

import com.paularolim.grocerylist.api.utils.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun ApplicationCall.customResponse(response: Response) {
    when (response) {
        is Response.Success -> {
            respond(
                response.statusCode ?: HttpStatusCode.OK,
                mapOf("message" to response.message)
            )
        }
        is Response.SuccessWithData<*> -> {
            respond(
                response.statusCode ?: HttpStatusCode.OK,
                response.data
            )
        }
        is Response.Error -> {
            respond(
                response.statusCode ?: HttpStatusCode.InternalServerError,
                mapOf(
                    "statusCode" to response.statusCode?.value.toString(),
                    "error" to response.error
                )
            )
        }
    }
}