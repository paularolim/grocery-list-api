package com.paularolim.grocerylist.api.common.main.configuration

import io.github.cdimascio.dotenv.Dotenv

object Environment {
    private val env = System.getenv("KTOR_ENV") ?: "development"

    private fun load(): Dotenv? {
        return Dotenv.configure().filename(".env.$env").load()
    }

    fun get(name: String): String {
        return load()?.get(name) ?: ""
    }
}