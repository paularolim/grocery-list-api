package com.paularolim.grocerylist.api.features.user.presentation.controllers

import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Controller
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.utils.Response
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlin.Exception

class UserRegisterController(
    private val validation: Validation<Any>
) : Controller<JsonObject> {
    @Serializable
    data class UserRegisterControllerRequest (
        val name: String?,
        val email: String?
    )

    override suspend fun handle(request: JsonObject): Response {
        try {
            val nameField = request["name"]?.jsonPrimitive?.contentOrNull
            val emailField = request["email"]?.jsonPrimitive?.contentOrNull

            val body = UserRegisterControllerRequest(nameField, emailField)

            val error = validation.validate(body)
            if (error != null) {
                throw error
            }
            return Response.Success("Success message")
        } catch (exception: MissingParamException) {
            val message = exception.message ?: ""
            return Response.Error(message, HttpStatusCode.BadRequest)
        } catch (exception: Exception) {
            val message = exception.message ?: ""
            return Response.Error(message, HttpStatusCode.InternalServerError)
        }
    }
}