package com.paularolim.grocerylist.api.features.user.domain.usecases

interface UserRegisterUsecase {
    data class RegisterParams (
        val name: String,
        val email: String,
        val password: String,
    )

    suspend fun handle(user: RegisterParams) : Boolean
}
