package com.paularolim.grocerylist.api.features.user.main.factories.usecases

import com.paularolim.grocerylist.api.common.infra.database.mongodb.repositories.UserMongodbRepository
import com.paularolim.grocerylist.api.features.user.data.usecases.DatabaseUserRegisterUsecase
import com.paularolim.grocerylist.api.features.user.domain.usecases.UserRegisterUsecase

fun makeUserRegisterUsecase(): UserRegisterUsecase {
    val repository = UserMongodbRepository()
    return DatabaseUserRegisterUsecase(repository)
}