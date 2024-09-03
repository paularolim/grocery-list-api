package com.paularolim.grocerylist.api.features.user.data.protocols

import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase

interface UserRegisterRepository {
    suspend fun register(data: UserRegisterUsecase.RegisterParams): Boolean
}