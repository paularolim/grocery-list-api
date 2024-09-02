package com.paularolim.grocerylist.api

import com.paularolim.grocerylist.api.plugins.routing.configureRouting
import com.paularolim.grocerylist.api.plugins.serialization.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
