package com.paularolim.grocerylist.api.utils

import io.ktor.http.*

sealed class Response {
    data class Success(
        val message: String,
        val statusCode: HttpStatusCode? = HttpStatusCode.OK
    ): Response()

    data class SuccessWithData<T : Any>(
        val data: T,
        val statusCode: HttpStatusCode? = HttpStatusCode.OK
    ): Response()

    data class Error(
        val error: String,
        val statusCode: HttpStatusCode? = HttpStatusCode.InternalServerError
    ): Response()
}

