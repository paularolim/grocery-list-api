package com.paularolim.grocerylist.api.features.user.main.factories.usecases

import com.paularolim.grocerylist.api.common.infra.database.mongodb.repositories.UserMongodbRepository
import com.paularolim.grocerylist.api.features.user.data.usecases.DatabaseUserLoginUsecase
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserLoginUsecase

fun makeUserLoginUsecase(): UserLoginUsecase {
    val repository = UserMongodbRepository()
    return DatabaseUserLoginUsecase(repository)
}