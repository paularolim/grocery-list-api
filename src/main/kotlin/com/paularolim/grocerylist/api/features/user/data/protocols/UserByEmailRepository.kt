package com.paularolim.grocerylist.api.features.user.data.protocols

interface UserByEmailRepository {
    data class Result (
        val id: String,
        val name: String,
        val email: String,
        val password: String,
    )

    suspend fun getByEmail(email: String) : Result?
}