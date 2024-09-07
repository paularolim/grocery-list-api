package com.paularolim.grocerylist.api.features.user.domain.usecases

interface UserLoginUsecase {
    data class Params (
        val email: String,
        val password: String,
    )

    data class Result (
        val name: String,
        val token: String,
    )

    suspend fun handle(params: Params): Result?
}