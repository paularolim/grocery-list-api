package com.paularolim.grocerylist.api.features.user.main.routes

import com.paularolim.grocerylist.api.features.user.main.factories.controllers.makeUserLoginController
import com.paularolim.grocerylist.api.features.user.main.factories.controllers.makeUserRegisterController
import com.paularolim.grocerylist.api.plugins.routing.customResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonObject

fun Route.userRoutes() {
    route("/users") {
        post("/register") {
            val body = call.receive<JsonObject>()
            call.customResponse(makeUserRegisterController().handle(body))
        }

        post("/login") {
            val body = call.receive<JsonObject>()
            call.customResponse(makeUserLoginController().handle(body))
        }
    }
}