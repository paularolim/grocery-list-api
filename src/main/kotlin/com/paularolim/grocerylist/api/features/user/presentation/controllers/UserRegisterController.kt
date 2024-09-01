package com.paularolim.grocerylist.api.features.user.presentation.controllers

import com.paularolim.grocerylist.api.common.presentation.protocols.Controller
import com.paularolim.grocerylist.api.utils.Response
import io.ktor.http.*
import kotlin.Exception

class UserRegisterController : Controller {
    override fun handle(request: Any): Response {
        try {
            return Response.Success("Success message")
        } catch (exception: Exception) {
            val message = exception.message ?: ""
            return Response.Error(message, HttpStatusCode.InternalServerError)
        }
    }
}