package com.paularolim.grocerylist.api.features.user.presentation.controllers

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Controller
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.utils.Response
import com.paularolim.grocerylist.api.utils.jsonToString
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

class UserLoginController(
    private val validation: Validation<Any>
) : Controller<JsonObject> {
    @Serializable
    data class UserLoginControllerRequest(
        val email: String?,
        val password: String?,
    )

    override suspend fun handle(request: JsonObject): Response {
        try {
            val emailField = jsonToString(request["email"])
            val passwordField = jsonToString(request["password"])

            val body = UserLoginControllerRequest(emailField, passwordField)

            val error = validation.validate(body)
            if (error != null) {
                throw error
            }

            return Response.Success("Mocked message", HttpStatusCode.Created)
        } catch (exception: MissingParamException) {
            val message = exception.message ?: ""
            return Response.Error(message, HttpStatusCode.BadRequest)
        } catch (exception: InvalidParamException) {
            val message = exception.message ?: ""
            return Response.Error(message, HttpStatusCode.BadRequest)
        } catch (exception: Exception) {
            val message = exception.message ?: ""
            return Response.Error(message, HttpStatusCode.InternalServerError)
        }
    }
}