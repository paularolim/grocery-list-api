package com.paularolim.grocerylist.api.features.user.main.routes

import com.paularolim.grocerylist.api.features.user.presentation.controllers.UserRegisterController
import com.paularolim.grocerylist.api.plugins.routing.customResponse
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    route("/users") {
        post("/register") {
            val controller = UserRegisterController().handle(call)
            call.customResponse(controller)
        }
    }
}