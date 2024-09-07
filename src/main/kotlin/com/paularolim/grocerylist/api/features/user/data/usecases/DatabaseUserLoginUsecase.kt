package com.paularolim.grocerylist.api.features.user.data.usecases

import com.paularolim.grocerylist.api.features.user.data.protocols.UserByEmailRepository
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserLoginUsecase

class DatabaseUserLoginUsecase(
    private val userByEmailRepository: UserByEmailRepository,
): UserLoginUsecase {
    override suspend fun handle(params: UserLoginUsecase.Params): UserLoginUsecase.Result? {
        val userFound = this.userByEmailRepository.getByEmail(params.email)
        if (userFound != null) {
            TODO("check password")
            return null
        }
        return null
    }
}