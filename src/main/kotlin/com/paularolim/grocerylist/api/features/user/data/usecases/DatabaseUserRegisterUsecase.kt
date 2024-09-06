package com.paularolim.grocerylist.api.features.user.data.usecases

import com.paularolim.grocerylist.api.common.data.protocols.cryptography.Hasher
import com.paularolim.grocerylist.api.features.user.data.protocols.UserRegisterRepository
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase

class DatabaseUserRegisterUsecase(
    private val repository: UserRegisterRepository,
    private val hasher: Hasher
) : UserRegisterUsecase {
    override suspend fun handle(user: UserRegisterUsecase.RegisterParams): Boolean {
        val hashedPassword = this.hasher.hash(user.password)
        return repository.register(UserRegisterUsecase.RegisterParams(
            name = user.name,
            email = user.email,
            password = hashedPassword,
        ))
    }

}