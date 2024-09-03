package com.paularolim.grocerylist.api.features.user.presentation.controllers

import com.paularolim.grocerylist.api.common.presentation.errors.InvalidParamException
import com.paularolim.grocerylist.api.common.presentation.errors.MissingParamException
import com.paularolim.grocerylist.api.common.presentation.protocols.Controller
import com.paularolim.grocerylist.api.common.presentation.protocols.Validation
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase
import com.paularolim.grocerylist.api.utils.Response
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlin.Exception

class UserRegisterController(
    private val validation: Validation<Any>,
    private val userRegister: UserRegisterUsecase
) : Controller<JsonObject> {
    @Serializable
    data class UserRegisterControllerRequest(
        val name: String?,
        val email: String?,
        val password: String?,
        val passwordConfirmation: String?
    )

    override suspend fun handle(request: JsonObject): Response {
        try {
            val nameField = request["name"]?.jsonPrimitive?.contentOrNull
            val emailField = request["email"]?.jsonPrimitive?.contentOrNull
            val passwordField = request["password"]?.jsonPrimitive?.contentOrNull
            val passwordConfirmationField = request["passwordConfirmation"]?.jsonPrimitive?.contentOrNull

            val body = UserRegisterControllerRequest(nameField, emailField, passwordField, passwordConfirmationField)

            val error = validation.validate(body)
            if (error != null) {
                throw error
            }

            val wasInserted = this.userRegister.handle(
                UserRegisterUsecase.RegisterParams(
                    body.name!!,
                    body.email!!,
                    body.password!!,
                    body.passwordConfirmation!!
                )
            )
            return if (wasInserted) {
                Response.Success("Success message", HttpStatusCode.Created)
            } else {
                Response.Error("Error message")
            }
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