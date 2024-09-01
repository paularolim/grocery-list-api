package com.paularolim.grocerylist.api.plugins.routing

import com.paularolim.grocerylist.api.features.user.main.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRoutes()
    }
}
