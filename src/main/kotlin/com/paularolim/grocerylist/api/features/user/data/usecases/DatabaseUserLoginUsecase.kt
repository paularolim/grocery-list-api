package com.paularolim.grocerylist.api.features.user.data.usecases

import com.paularolim.grocerylist.api.common.data.protocols.cryptography.HashComparer
import com.paularolim.grocerylist.api.features.user.data.protocols.UserByEmailRepository
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserLoginUsecase

class DatabaseUserLoginUsecase(
    private val userByEmailRepository: UserByEmailRepository,
    private val hashComparer: HashComparer
): UserLoginUsecase {
    override suspend fun handle(params: UserLoginUsecase.Params): UserLoginUsecase.Result? {
        val userFound = this.userByEmailRepository.getByEmail(params.email)
        if (userFound != null) {
            val isValid = this.hashComparer.compare(params.password, userFound.password)
            if (isValid) {
                TODO("generate token")
            }
        }
        return null
    }
}