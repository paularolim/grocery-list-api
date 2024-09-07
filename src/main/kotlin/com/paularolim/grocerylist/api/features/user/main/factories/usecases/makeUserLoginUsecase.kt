package com.paularolim.grocerylist.api.features.user.main.factories.usecases

import com.paularolim.grocerylist.api.common.infra.cryptography.BcryptAdapter
import com.paularolim.grocerylist.api.common.infra.database.mongodb.repositories.UserMongodbRepository
import com.paularolim.grocerylist.api.features.user.data.usecases.DatabaseUserLoginUsecase
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserLoginUsecase

fun makeUserLoginUsecase(): UserLoginUsecase {
    val repository = UserMongodbRepository()
    val salt = 12
    val hasher = BcryptAdapter(salt)
    return DatabaseUserLoginUsecase(repository, hasher)
}