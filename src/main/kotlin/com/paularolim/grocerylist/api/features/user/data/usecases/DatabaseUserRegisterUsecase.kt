package com.paularolim.grocerylist.api.features.user.data.usecases

import com.paularolim.grocerylist.api.features.user.data.protocols.UserRegisterRepository
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase

class DatabaseUserRegisterUsecase(
    private val repository: UserRegisterRepository
) : UserRegisterUsecase {
    override suspend fun handle(user: UserRegisterUsecase.RegisterParams): Boolean {
        return repository.register(user)
    }

}